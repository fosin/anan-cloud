{{/*
anan secret模版
*/}}
{{- define "anan.secret" -}}
{{- if not $.Values.secret.existSecretName }}
apiVersion: {{ $.Values.secret.apiVersion | default "v1" }}
kind: Secret
type: {{ $.Values.secret.type }}
metadata:
  name: {{ include "anan.secret.name" . }}
  namespace: {{ .Release.Namespace }}
  {{- with .Values.secret.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
{{- if (.Values.secret.b64enc | default true) }}
data:
  {{- range $key, $val := .Values.secret.confs }}
  {{ $key }}: {{ $val | b64enc | quote }}
  {{- end }}
  {{- range $index, $files := $.Values.secret.files }}
  {{- range $fileName, $file := $files.mountFiles }}
  {{ $fileName }}: {{ $file | b64enc | quote }}
  {{- end }}
  {{- end }}
{{- else }}
stringData:
  {{- with $.Values.secret.confs }}
    {{- toYaml . | nindent 2 }}
  {{- end }}
  {{- range $index, $files := $.Values.secret.files }}
    {{- toYaml $files.mountFiles | nindent 2 }}
  {{- end }}
{{- end }}
{{- end }}
{{- end -}}

{{/*
anan default secret name
*/}}
{{- define "anan.secret.name" -}}
{{ $.Release.Name }}
{{- end -}}
