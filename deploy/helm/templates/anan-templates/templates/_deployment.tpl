{{/*
anan deployment模版
*/}}
{{- define "anan.deployment" -}}
{{ include "anan.replicaset" . }}
{{- end -}}
