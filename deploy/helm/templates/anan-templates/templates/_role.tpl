{{/*
anan role
*/}}
{{- define "anan.role" -}}
---
apiVersion: {{ $.Values.role.apiVersion | default "rbac.authorization.k8s.io/v1" }}
kind: Role
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.role.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.role.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
{{- with $.Values.role.rules }}
rules:
{{ toYaml . | nindent 2}}
{{ end }}
{{- end -}}

{{/*
anan rolebinding
*/}}
{{- define "anan.rolebinding" -}}
---
kind: RoleBinding
apiVersion: {{ $.Values.role.apiVersion | default "rbac.authorization.k8s.io/v1" }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.role.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.role.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: {{ $.Release.Name }}
subjects:
  - kind: ServiceAccount
    name: {{ $.Values.role.serviceAccountName | default $.Release.Name }}
    namespace: {{ $.Release.Namespace }}
    {{/*apiGroup: #Defaults to "" for ServiceAccount subjects. Defaults to "rbac.authorization.k8s.io" for User and
    Group subjects.*/}}
{{- end -}}
