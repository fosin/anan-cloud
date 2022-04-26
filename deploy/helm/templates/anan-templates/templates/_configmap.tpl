{{/*
anan configmap模版
*/}}
{{- define "anan.configmap" -}}
{{- $configmapName := (include "anan.configmap.name" .) }}
{{- $cmComposes := list }}
{{- range $x,$cm := $.Values.configmap }}
  {{- if not $cm.existName }}
    {{- $finded := false }}
    {{- $source := ($cm.name | default $configmapName) }}
    {{- range $y,$cmCompose := $cmComposes }}
      {{- $target := ($cmCompose.name | default $configmapName) }}
      {{- if eq $source $target }}
         {{- $finded = true }}
         {{- $cmCompose = set $cmCompose "name" $source }}
         {{- $cmCompose = set $cmCompose "fromMap" (mergeOverwrite ($cmCompose.fromMap | default dict) ($cm.fromMap | default dict)) }}
         {{- $cmCompose = set $cmCompose "fromFiles" (mergeOverwrite ($cmCompose.fromFiles | default dict) ($cm.fromFiles | default dict)) }}
         {{- $cmCompose = set $cmCompose "fromTemps" (mergeOverwrite ($cmCompose.fromTemps | default dict) ($cm.fromTemps | default dict)) }}
         {{- $cmCompose = set $cmCompose "fromDirs" (concat ($cmCompose.fromDirs | default list) ($cm.fromDirs | default list)) }}
      {{- end }}
    {{- end }}
    {{- if not $finded }}
       {{- $new := mergeOverwrite dict $cm }}
       {{- $new = set $new "name" $source }}
       {{- $new = set $new "fromTemps" ($new.fromTemps | default dict) }}
       {{- $cmComposes = append $cmComposes $new }}
    {{- end }}
  {{- end }}
{{- end }}

{{- range $x,$cm := $cmComposes }}
---
apiVersion: {{ $cm.apiVersion | default "v1" }}
kind: ConfigMap
metadata:
  name: {{ $cm.name }}
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
{{- $allFiles := (mergeOverwrite $fromFiles $cm.fromTemps $dirFiles) }}
{{- range $key, $val := $allFiles }}
  {{ $key }}: |-
    {{- $val | nindent 4 }}
{{- end }}
{{- end }}
{{- end -}}

{{/*
anan default config name
*/}}
{{- define "anan.configmap.name" -}}
{{ $.Release.Name }}
{{- end -}}

