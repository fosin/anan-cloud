{{/*
anan configmap模版
*/}}
{{- define "anan.configmap" -}}
{{- $configmapName := (include "anan.configmap.name" .) }}
{{- range $x,$cm := $.Values.configmap }}
{{- if not $cm.existName }}
apiVersion: {{ $cm.apiVersion | default "v1" }}
kind: ConfigMap
metadata:
  name: {{ $cm.name | default $configmapName }}
  namespace: {{ $.Release.Namespace }}
  labels:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
    {{- with $cm.labels }}
    {{- toYaml . | nindent 4 }}
    {{- end }}
  {{- with $cm.annotations }}
  annotations:
    {{- toYaml . | nindent 4 }}
  {{- end }}
data:
{{- with $cm.fromMap }}
  {{- toYaml . | nindent 2 }}
{{- end }}
{{- $dirFiles := dict }}
{{- range $index, $dir := $cm.fromDirs }}
  {{- range $path, $_ := $.Files.Glob $dir }}
    {{- $dirFiles := (set $dirFiles (base $path) ($.Files.Get $path)) }}
  {{- end }}
{{- end }}
{{- $fromFiles := dict }}
{{- range $key, $val := $cm.fromFiles }}
  {{- $fromFiles := (set $fromFiles $key ($.Files.Get $val)) }}
{{- end }}
{{- $allFiles := (mergeOverwrite $fromFiles ($cm.fromTemps | default dict) $dirFiles) }}
{{- range $key, $val := $allFiles }}
  {{ $key }}: |-
    {{- $val | nindent 4 }}
{{- end }}
---
{{- end }}
{{- end }}
{{- end -}}

{{/*
anan default config name
*/}}
{{- define "anan.configmap.name" -}}
{{ $.Release.Name }}
{{- end -}}
