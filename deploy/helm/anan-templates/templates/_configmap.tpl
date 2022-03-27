{{/*
anan configmap模版
*/}}
{{- define "anan.configmap" -}}
{{- if not $.Values.configmap.existConfigMapName }}
apiVersion: {{ $.Values.configmap.apiVersion | default "v1" }}
kind: ConfigMap
metadata:
  name: {{ include "anan.configmap.name" . }}
  namespace: {{ .Release.Namespace }}
  {{- with $.Values.configmap.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
data:
{{- with $.Values.configmap.confs }}
  {{- toYaml . | nindent 2 }}
{{- end }}
{{- range $index, $files := $.Values.configmap.files }}
  {{- toYaml $files.mountFiles | nindent 2 }}
{{- end }}
{{- end }}
{{- end -}}

{{/*
anan default config name
*/}}
{{- define "anan.configmap.name" -}}
{{ $.Release.Name }}
{{- end -}}
