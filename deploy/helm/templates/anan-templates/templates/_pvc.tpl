{{/*
anan nfs PersistentVolumeClaim
*/}}
{{- define "anan.pvc" -}}
{{- if $.Values.persistence }}
{{- range $x,$datax := .Values.persistence }}
{{- range $y,$datay := $datax.nfs }}
---
apiVersion: {{ $datax.apiVersion | default "v1" }}
kind: PersistentVolumeClaim
metadata:
  name: {{ $datax.name }}-{{ $.Release.Name }}-{{ $y }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $y }}
  {{- with $datax.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $datax.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- with $datax.storageClassName }}
  storageClassName: {{ toYaml . }}
  {{- end }}
  accessModes:
    - {{ $datax.accessMode }}
  resources:
    requests:
      storage: {{ $datax.size }}i
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $y }}
{{- end }}
{{- range $z,$dataz := $datax.local }}
---
apiVersion: {{ $datax.apiVersion | default "v1" }}
kind: PersistentVolumeClaim
metadata:
  name: {{ $datax.name }}-{{ $.Release.Name }}-{{ $z }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $z }}
  {{- with $datax.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $datax.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- with $datax.storageClassName }}
  storageClassName: {{ toYaml . }}
  {{- end }}
  accessModes:
    - {{ $datax.accessMode }}
  resources:
    requests:
      storage: {{ $datax.size }}i
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $datax.name }}-{{ $.Release.Name }}-{{ $z }}
{{- end }}
{{- end }}
{{- end }}
{{- end -}}
