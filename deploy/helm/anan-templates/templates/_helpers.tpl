{{/* vim: set filetype=mustache: */}}
{{/*
anan workdir
*/}}
{{- define "anan.workdir" -}}
/data
{{- end -}}

{{/*
anan nfs workdir
*/}}
{{- define "anan.workdir.nfs" -}}
{{ include "anan.workdir" . }}/nfs/
{{- end -}}


{{/*
anan user
*/}}
{{- define "anan.user" -}}
anan
{{- end -}}

{{/*
anan password
*/}}
{{- define "anan.password" -}}
local
{{- end -}}

{{/*
anan tz
*/}}
{{- define "anan.tz" -}}
Asia/Shanghai
{{- end -}}

{{/*
anan lable name
*/}}
{{- define "anan.lable.name" -}}
name
{{- end -}}