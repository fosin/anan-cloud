
{{/*
anan Ingress模版
*/}}
{{- define "anan.ingress" -}}
apiVersion: {{ $.Values.ingress.apiVersion | default "networking.k8s.io/v1" }}
kind: Ingress
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.ingress.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.ingress.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- with $.Values.ingress.tls }}
  tls:
    {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- if $.Values.ingress.ingressClassName }}
  ingressClassName: {{ $.Values.ingress.ingressClassName }}
  {{- else }}
  ingressClassName: {{ $.Release.Name }}
  {{- end }}
  {{- with $.Values.ingress.defaultBackend }}
  {{- if $.Values.ingress.apiVersion }}
  {{- if eq $.Values.ingress.apiVersion "extensions/v1beta1" }}
  backend:
  {{- else }}
  defaultBackend:
  {{- end }}
  {{- else }}
  defaultBackend:
  {{- end }}
    {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.ingress.rules }}
  rules:
    {{- toYaml . | nindent 4 }}
  {{- end }}
{{- end -}}


{{/*
anan ingressClass模版
*/}}
{{- define "anan.ingressClass" -}}
apiVersion: {{ $.Values.ingressClass.apiVersion | default "networking.k8s.io/v1" }}
kind: IngressClass
metadata:
  name: {{ $.Release.Name }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.ingressClass.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- if or $.Values.ingressClass.default $.Values.ingressClass.annotations }}
  annotations:
  {{- if $.Values.ingressClass.default }}
    ingressclass.kubernetes.io/is-default-class: "true"
  {{- end }}
  {{- with $.Values.ingressClass.annotations }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- end }}
spec:
  controller: {{ $.Values.ingressClass.controller | default "nginx.org/ingress-controller" }}
  {{- with $.Values.ingressClass.parameters }}
  parameters:
    {{ toYaml . | indent 4 }}
  {{- end }}
{{- end -}}
