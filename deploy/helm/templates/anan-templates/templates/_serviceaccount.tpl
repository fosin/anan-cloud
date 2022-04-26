
{{/*
anan serviceaccount
*/}}
{{- define "anan.serviceaccount" -}}
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
{{- end -}}
