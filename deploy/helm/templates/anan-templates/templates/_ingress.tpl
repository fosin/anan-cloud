
{{/*
anan Ingress模版
*/}}
{{- define "anan.ingress" }}
---
apiVersion: {{ default (include "anan.ingress.apiVersion" .) $.Values.ingress.apiVersion }}
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
  ingressClassName: {{ default $.Release.Name $.Values.ingress.ingressClassName }}
  {{- with $.Values.ingress.defaultBackend }}
  {{- if (include "anan.ingress.isStable" .) }}
  backend:
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
{{- define "anan.ingressClass" }}
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

{{/* Get Ingress API Version */}}
{{- define "anan.ingress.apiVersion" }}
  {{- if and (.Capabilities.APIVersions.Has "networking.k8s.io/v1") (semverCompare ">= 1.19.0" $.Capabilities.KubeVersion.Version) }}
      {{- print "networking.k8s.io/v1" }}
  {{- else if .Capabilities.APIVersions.Has "networking.k8s.io/v1beta1" }}
    {{- print "networking.k8s.io/v1beta1" }}
  {{- else }}
    {{- print "extensions/v1beta1" }}
  {{- end }}
{{- end }}

{{/* Check Ingress stability */}}
{{- define "anan.ingress.isStable" }}
  {{- eq (include "anan.ingress.apiVersion" .) "networking.k8s.io/v1" }}
{{- end }}

{{/* Check Ingress supports pathType */}}
{{/* pathType was added to networking.k8s.io/v1beta1 in Kubernetes 1.18 */}}
{{- define "anan.ingress.supportsPathType" }}
  {{- or (eq (include "anan.ingress.isStable" .) "true") (and (eq (include "anan.ingress.apiVersion" .) "networking.k8s.io/v1beta1") (semverCompare ">= 1.18.0" $.Capabilities.KubeVersion.Version)) }}
{{- end }}
