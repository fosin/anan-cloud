
{{/*
anan cluster role
*/}}
{{- define "anan.cluster.role" -}}
apiVersion: {{ $.Values.cluster.role.apiVersion | default "rbac.authorization.k8s.io/v1" }}
kind: ClusterRole
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.cluster.role.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.cluster.role.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
{{- with $.Values.cluster.role.rules }}
rules:
{{- toYaml . | nindent 2}}
{{- end }}
{{- end -}}

{{/*
anan cluster rolebinding
*/}}
{{- define "anan.cluster.rolebinding" -}}
kind: ClusterRoleBinding
apiVersion: {{ $.Values.cluster.role.apiVersion | default "rbac.authorization.k8s.io/v1" }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- with $.Values.cluster.role.labels }}
  {{- toYaml . | nindent 4 }}
  {{- end }}
  {{- with $.Values.cluster.role.annotations }}
  annotations:
  {{- toYaml . | nindent 4 }}
  {{- end }}
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: {{ $.Release.Name }}
subjects:
  - kind: ServiceAccount
    name: {{ $.Values.cluster.serviceAccountName | default $.Release.Name }}
    namespace: {{ $.Release.Namespace }}
{{- end -}}

