{{/*
anan cronjob
*/}}
{{- define "anan.cronjob" -}}
---
kind: CronJob
apiVersion: {{ $.Values.cronjob.apiVersion | default "batch/v1beta1" }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  {{- with $.Values.cronjob.schedule | quote }}
  schedule: {{ . }}
  {{- end }}
  {{- with $.Values.cronjob.startingDeadlineSeconds }}
  startingDeadlineSeconds: {{ . }}
  {{- end }}
  {{- with $.Values.cronjob.concurrencyPolicy }}
  concurrencyPolicy: {{ . }}
  {{- end }}
  {{- with $.Values.cronjob.successfulJobsHistoryLimit }}
  successfulJobsHistoryLimit: {{ . }}
  {{- end }}
  {{- with $.Values.cronjob.failedJobsHistoryLimit }}
  failedJobsHistoryLimit: {{ . }}
  {{- end }}
  jobTemplate:
    metadata:
      labels:
        {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
        {{- with $.Values.cronjob.labels }}
        {{- toYaml . | nindent 8 }}
        {{ end }}
        {{- with $.Values.cronjob.annotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
        {{ end }}
    spec:
      {{- include "anan.job.spec" . | nindent 6 }}
{{- end -}}
