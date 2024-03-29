{{- define "anan.include.all"}}
{{- if .Values.configmap }}
{{ include "anan.configmap" . }}
{{- end }}

{{- if .Values.secret }}
{{ include "anan.secret" . }}
{{- end }}

{{- if .Values.cluster }}
{{ include "anan.cluster.role" . }}
{{ include "anan.cluster.rolebinding" . }}
{{- end }}

{{- if .Values.role }}
{{ include "anan.role" . }}
{{ include "anan.rolebinding" . }}
{{- end }}

{{- if or .Values.cluster .Values.role }}
{{ include "anan.serviceaccount" . }}
{{- end }}

{{- if .Values.persistence }}
{{ include "anan.pv" . }}
{{ include "anan.pvc" . }}
{{- end }}

{{- if .Values.ingressClass }}
{{ include "anan.ingressClass" . }}
{{- end }}
{{- if .Values.ingress }}
{{ include "anan.ingress" . }}
{{- end }}

{{ if .Values.service }}
{{- if .Values.statefulset }}
{{ include "anan.service.headless" . }}
{{ end }}
{{ include "anan.service" . }}
{{ end }}

{{- if .Values.deployment }}
{{ include "anan.deployment" . }}
{{- end }}

{{- if .Values.statefulset }}
{{ include "anan.statefulset" . }}
{{- end }}

{{- if .Values.daemonset }}
{{ include "anan.daemonset" . }}
{{- end }}

{{- if .Values.job }}
{{ include "anan.job" . }}
{{- end }}

{{- if .Values.cronjob }}
{{ include "anan.cronjob" . }}
{{- end }}
{{- end -}}
