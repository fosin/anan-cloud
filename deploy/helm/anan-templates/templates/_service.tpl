
{{/*
anan service name
*/}}
{{- define "anan.service.name" -}}
{{ $.Release.Name }}
{{- end -}}

{{/*
anan headless service name
*/}}
{{- define "anan.service.headless.name" -}}
{{ $.Release.Name }}-headless
{{- end -}}

{{/*
anan service
*/}}
{{- define "anan.service" -}}
{{- $serviceType := $.Values.service.type | default "ClusterIP" }}
kind: Service
apiVersion: {{ $.Values.service.apiVersion | default "v1" }}
metadata:
  name: {{ include "anan.service.name" . }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if $.Values.service.lables }}
  {{- $.Values.service.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- end }}
  {{- with $.Values.service.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  ports:
    {{- range $.Values.service.ports }}
    {{- if .port }}
    - name: {{ .name }}
      port: {{ .port }}
      targetPort: {{ .targetPort | default .port }}
      {{- if .protocol }}
      protocol: {{ .protocol }}
      {{- end }}
      {{- if eq $serviceType "NodePort" }}
      nodePort: {{ .nodePort | default .port }}
      {{- end }}
    {{- end }}
    {{- end }}
  selector:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  type: {{ $serviceType }}
  {{- if eq $serviceType "ExternalName" }}
  externalName: {{ $.Values.service.externalName }}
  {{- end }}
  {{- with $.Values.service.externalIPs }}
  externalIPs:
  {{ toYaml . | nindent 4}}
  {{- end }}
{{- end -}}

{{/*
anan headless service：一般用于statefulset集群内部互相访问
*/}}
{{- define "anan.service.headless" -}}
kind: Service
apiVersion: {{ $.Values.service.apiVersion | default "v1" }}
metadata:
  name: {{ include "anan.service.headless.name" . }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if $.Values.service.lables }}
  {{- $.Values.service.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ include "anan.service.headless.name" . }}
  {{- end }}
spec:
  type: ClusterIP
  clusterIP: None
  # publishNotReadyAddresses, when set to true, indicates that DNS implementations must publish
  # the notReadyAddresses of subsets for the Endpoints associated with the Service.
  # The default value is false. The primary use case for setting this field is to use a StatefulSet's
  # Headless Service to propagate SRV records for its Pods without respect to their readiness for purpose
  # of peer discovery. This field will replace the service.alpha.kubernetes.io/tolerate-unready-endpoints
  # when that annotation is deprecated and all clients have been converted to use this field.
  # 由于使用DNS访问Pod需Pod和Headless service启动之后才能访问，publishNotReadyAddresses设置成true，
  # 防止readinessProbe在服务没启动时找不到DNS
  publishNotReadyAddresses: {{ $.Values.service.publishNotReadyAddresses | default true }}
  ports:
    {{- range $.Values.service.ports }}
    - name: {{ .name }}
      port: {{ .port }}
      targetPort: {{ .targetPort | default .port }}
      {{- if .protocol }}
      protocol: {{ .protocol }}
      {{- end }}
    {{- end }}
  selector:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
{{- end -}}
