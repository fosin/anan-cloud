{{/*
anan PersistentVolume
*/}}
{{- define "anan.pv" -}}
{{- if $.Values.persistence }}
{{- range $x,$datax := .Values.persistence }}
{{- range $y,$datay := $datax.nfs }}
apiVersion: {{ $datax.apiVersion | default "v1" }}
kind: PersistentVolume
metadata:
  name: {{ $datax.name }}-{{ $.Release.Name }}-{{ $y }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $y }}
  {{- with $datax.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $datax.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- with $datax.storageClassName }}
  storageClassName: {{ toYaml . }}
  {{- end }}
  capacity:
    storage: {{ $datax.size }}i
  accessModes:
    - {{ $datax.accessMode }}
  {{- with $datax.persistentVolumeReclaimPolicy }}
  persistentVolumeReclaimPolicy: {{ toYaml . }}
  {{- end }}
  nfs:
    server: {{ $datay.server }}
    {{- with $datay.readOnly }}
    readOnly: {{ . }}
    {{- end }}
    {{- if $datay.path }}
    path: {{ $datay.path }}
    {{- else }}
    path: {{ include "anan.workdir.nfs" . }}{{ $.Release.Name }}_{{ $datax.name }}_{{ $y }}
    {{- end }}
---
{{- end }}
{{- range $z,$dataz := $datax.local }}
apiVersion: {{ $datax.apiVersion | default "v1" }}
kind: PersistentVolume
metadata:
  name: {{ $datax.name }}-{{ $.Release.Name }}-{{ $z }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $z }}
  {{- with $datax.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $datax.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- with $datax.storageClassName }}
  storageClassName: {{ toYaml . }}
  {{- end }}
  capacity:
    storage: {{ $datax.size }}i
  accessModes:
    - {{ $datax.accessMode }}
  {{- with $datax.persistentVolumeReclaimPolicy }}
  persistentVolumeReclaimPolicy: {{ toYaml . }}
  {{- end }}
  local:
    fsType: {{ $dataz.fsType }}
    path: {{ $dataz.path }}
  nodeAffinity:
  {{- if $datax.nodeAffinity }}
  {{- $datax.nodeAffinity | nindent 4 }}
  {{- else }}
  {{- if $dataz.hostname }}
    required:
      nodeSelectorTerms:
      - matchExpressions:
        - key: kubernetes.io/hostname
          operator: In
          values:
          - {{ $dataz.hostname }}
  {{- end }}
  {{- end }}
---
{{- end }}
{{- end }}
{{- end -}}
{{- end }}
