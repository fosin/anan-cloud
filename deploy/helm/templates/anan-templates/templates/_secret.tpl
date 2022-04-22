{{/*
anan secret模版
*/}}
{{- define "anan.secret" -}}
{{- $secretName := (include "anan.secret.name" .) }}
{{- range $x, $secret := $.Values.secret }}
{{- if not $secret.existName }}
apiVersion: {{ $secret.apiVersion | default "v1" }}
kind: Secret
type: {{ $secret.type }}
metadata:
  name: {{ $secret.name | default $secretName }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $secret.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $secret.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
{{- $b64enc := ($secret.b64enc | default true) }}
{{- $dirFiles := dict }}
{{- range $index, $dir := $secret.fromDirs }}
  {{- range $path, $_ := $.Files.Glob $dir }}
    {{- $dirFiles := (set $dirFiles (base $path) ($.Files.Get $path)) }}
  {{- end }}
{{- end }}
{{- $fromFiles := dict }}
{{- range $key, $val := $secret.fromFiles }}
  {{- $fromFiles := (set $fromFiles $key ($.Files.Get $val)) }}
{{- end }}
{{- $fromMap := $secret.fromMap | default dict }}
{{- $allFiles := (mergeOverwrite $fromMap $fromFiles ($secret.fromTemps | default dict) $dirFiles) }}
{{- if $b64enc }}
data:
  {{- range $key, $val := $allFiles }}
  {{ $key }}: {{ $val | b64enc | quote  }}
  {{- end }}
{{- else }}
stringData:
  {{- range $key, $val := $allFiles }}
  {{ $key }}: {{ $val }}
  {{- end }}
{{- end }}
---
{{- end }}
{{- end }}
{{- end -}}

{{/*
anan default secret name
*/}}
{{- define "anan.secret.name" -}}
{{ $.Release.Name }}
{{- end -}}
