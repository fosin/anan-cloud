{{/*
anan secret模版
*/}}
{{- define "anan.secret" -}}
{{- $secretName := (include "anan.secret.name" .) }}
{{- $sectComposes := list }}
{{- range $x,$secret := $.Values.secret }}
  {{- if not $secret.existName }}
    {{- $finded := false }}
    {{- $source := ($secret.name | default $secretName) }}
    {{- range $y,$sectCompose := $sectComposes }}
      {{- $target := ($sectCompose.name | default $secretName) }}
      {{- if eq $source $target }}
         {{- $finded = true }}
         {{- $sectCompose = set $sectCompose "name" $source }}
         {{- $sectCompose = set $sectCompose "fromMap" (mergeOverwrite ($sectCompose.fromMap | default dict) ($secret.fromMap | default dict)) }}
         {{- $sectCompose = set $sectCompose "fromFiles" (mergeOverwrite ($sectCompose.fromFiles | default dict) ($secret.fromFiles | default dict)) }}
         {{- $sectCompose = set $sectCompose "fromTemps" (mergeOverwrite ($sectCompose.fromTemps | default dict) ($secret.fromTemps | default dict)) }}
         {{- $sectCompose = set $sectCompose "fromDirs" (concat ($sectCompose.fromDirs | default list) ($secret.fromDirs | default list)) }}
      {{- end }}
    {{- end }}
    {{- if not $finded }}
       {{- $new := mergeOverwrite dict $secret }}
       {{- $new = set $new "name" $source }}
       {{- $new = set $new "fromTemps" ($new.fromTemps | default dict) }}
       {{- $sectComposes = append $sectComposes $new }}
    {{- end }}
  {{- end }}
{{- end }}


{{- range $x, $secret := $sectComposes }}
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
{{- $allFiles := (mergeOverwrite $fromMap $fromFiles $secret.fromTemps $dirFiles) }}
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
{{- end -}}

{{/*
anan default secret name
*/}}
{{- define "anan.secret.name" -}}
{{ $.Release.Name }}
{{- end -}}
