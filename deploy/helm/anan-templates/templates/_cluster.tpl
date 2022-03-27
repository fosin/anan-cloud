
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
  {{- if .Values.cluster.role.lables }}
  {{- .Values.cluster.role.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
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
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: {{ $.Release.Name }}
subjects:
  - kind: ServiceAccount
    name: {{ $.Values.cluster.serviceAccountName | default $.Release.Name }}
    namespace: {{ $.Release.Namespace }}
{{- end -}}

