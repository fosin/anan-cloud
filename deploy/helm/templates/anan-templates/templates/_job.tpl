{{/*
anan job
*/}}
{{- define "anan.job" -}}
---
kind: Job
apiVersion: {{ $.Values.job.apiVersion | default "batch/v1" }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  {{- include "anan.job.spec" . | nindent 2 }}
{{- end -}}

{{- define "anan.job.spec" -}}
{{- $replicaset := or $.Values.cronjob $.Values.job }}
{{- with $replicaset.parallelism }}
parallelism: {{ . }}
{{- end }}
{{- with $replicaset.completions }}
completions: {{ . }}
{{- end }}
{{- with $replicaset.backoffLimit }}
backoffLimit: {{ . }}
{{- end }}
{{- with $replicaset.ttlSecondsAfterFinished }}
ttlSecondsAfterFinished: {{ . }}
{{- end }}
{{- with $replicaset.activeDeadlineSeconds }}
activeDeadlineSeconds: {{ . }}
{{- end }}
{{- with $replicaset.manualSelector }}
manualSelector: {{ . }}
{{- end }}
{{- if $replicaset.manualSelector }}
selector:
  matchLabels:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
{{- end }}
template:
  metadata:
    labels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
      {{- with $replicaset.labels }}
      {{- toYaml . | nindent 6 }}
      {{- end }}
      {{- with $replicaset.annotations }}
    annotations:
      {{- toYaml . | nindent 6 }}
      {{- end }}
  spec:
    {{- include "anan.podspec" . | nindent 4 }}
{{- end -}}
