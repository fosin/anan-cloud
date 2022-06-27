{{/*
anan replicaset 模版(包括：statefulset、deployment、daemonset)
*/}}
{{- define "anan.replicaset" -}}
---
{{- $replicaset := coalesce $.Values.statefulset $.Values.deployment $.Values.daemonset }}
{{- if $.Values.statefulset }}
kind: StatefulSet
apiVersion: {{ $replicaset.apiVersion | default "apps/v1" }}
{{- else if $.Values.deployment }}
kind: Deployment
apiVersion: {{ $replicaset.apiVersion | default "apps/v1" }}
{{- else if $.Values.daemonset }}
kind: DaemonSet
apiVersion: {{ $replicaset.apiVersion | default "apps/v1" }}
{{- end }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  {{- if $.Values.statefulset }}
  serviceName: {{ $.Values.statefulset.serviceName | default (include "anan.service.headless.name" .) }}
  podManagementPolicy: {{ $.Values.statefulset.podManagementPolicy | default "Parallel" }}
  {{- end }}
  {{- with $.Values.revisionHistoryLimit }}
  revisionHistoryLimit: {{ . }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
  {{- if $.Values.statefulset }}
    {{- with $.Values.statefulset.updateStrategy }}
  updateStrategy:
    {{- toYaml . | nindent 4 }}
    {{- end }}
    {{- with $.Values.statefulset.replicaCount }}
  replicas: {{ . }}
    {{- end }}
  {{- else if $.Values.deployment }}
    {{- with $.Values.deployment.replicaCount }}
  replicas: {{ . }}
    {{- end }}
    {{- with $.Values.deployment.strategy }}
  strategy:
    {{- toYaml . | nindent 4 }}
    {{- end }}
    {{- with $.Values.deployment.minReadySeconds }}
  minReadySeconds: {{ . }}
    {{- end }}
  {{- else if $.Values.daemonset }}
    {{- with $.Values.daemonset.updateStrategy }}
  updateStrategy:
      {{- toYaml . | nindent 4 }}
    {{- end }}
    {{- with $.Values.daemonset.minReadySeconds }}
  minReadySeconds: {{ . }}
    {{- end }}
  {{- end }}
  template:
    metadata:
      labels:
        {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
        {{- with $replicaset.labels }}
        {{- toYaml . | nindent 8 }}
        {{- end }}
        {{- with $replicaset.annotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
        {{- end }}
    spec:
      {{- include "anan.podspec" . | nindent 6 }}
  {{- if $.Values.statefulset }}
  {{- if $.Values.statefulset.volumeClaimTemplates }}
  volumeClaimTemplates:
  {{- toYaml $.Values.statefulset.volumeClaimTemplates | nindent 4 }}
  {{- else if $.Values.persistence }}
  volumeClaimTemplates:
  {{- range $x,$datax := .Values.persistence }}
    - metadata:
        name: {{ default (print $.Release.Name "-" $x) $datax.name }}
        namespace: {{ $.Release.Namespace }}
      spec:
        accessModes: [ {{ $datax.accessMode }} ]
        {{- with $datax.storageClassName }}
        storageClassName: {{ toYaml . }}
        {{- end }}
        resources:
          requests:
            storage: {{ $datax.size }}i
  {{- end }}
  {{- end }}
  {{- end }}
{{- end -}}

