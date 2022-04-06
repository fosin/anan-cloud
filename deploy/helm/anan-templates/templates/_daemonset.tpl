{{/*
anan daemonset模版
*/}}
{{- define "anan.daemonset" -}}
{{ include "anan.replicaset" . }}
{{- end -}}
