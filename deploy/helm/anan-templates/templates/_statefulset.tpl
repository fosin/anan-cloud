
{{/*
anan statefulset 模版
*/}}
{{- define "anan.statefulset" -}}
{{ include "anan.replicaset" . }}
{{- end -}}
