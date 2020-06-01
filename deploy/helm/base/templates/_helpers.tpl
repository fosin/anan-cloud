{{/* vim: set filetype=mustache: */}}
{{/*
anan workdir
*/}}
{{- define "anan.workdir" -}}
/root/deploy
{{- end -}}

{{/*
anan nfs workdir
*/}}
{{- define "anan.workdir.nfs" -}}
{{ include "anan.workdir" . }}/nfs/
{{- end -}}


{{/*
anan user
*/}}
{{- define "anan.user" -}}
anan
{{- end -}}

{{/*
anan password
*/}}
{{- define "anan.password" -}}
local
{{- end -}}

{{/*
anan tz
*/}}
{{- define "anan.tz" -}}
Asia/Shanghai
{{- end -}}

{{/*
anan lable name
*/}}
{{- define "anan.lable.name" -}}
name
{{- end -}}

{{/*
anan tz
*/}}
{{- define "anan.service.headless.name" -}}
{{ $.Release.Name }}-headless
{{- end -}}

{{/*
anan serviceaccount
*/}}
{{- define "anan.serviceaccount" -}}
apiVersion: v1
kind: ServiceAccount
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
{{- end -}}

{{/*
anan role
*/}}
{{- define "anan.role" -}}
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if .Values.role.lables }}
  {{- .Values.role.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
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
kind: RoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: {{ $.Release.Name }}
subjects:
  - kind: ServiceAccount
    name: {{ $.Release.Name }}
    namespace: {{ $.Release.Namespace }}
{{- end -}}

{{/*
anan cluster role
*/}}
{{- define "anan.cluster.role" -}}
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if .Values.clusterRole.lables }}
  {{- .Values.clusterRole.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- end }}
{{- with $.Values.clusterRole.rules }}
rules:
{{- toYaml . | nindent 2}}
{{- end }}
{{- end -}}

{{/*
anan cluster rolebinding
*/}}
{{- define "anan.cluster.rolebinding" -}}
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: {{ $.Release.Name }}
subjects:
  - kind: ServiceAccount
    name: {{ $.Release.Name }}
    namespace: {{ $.Release.Namespace }}
{{- end -}}

{{/*
anan nfs PersistentVolumeClaim
*/}}
{{- define "anan.persistentvolumeclaim.nfs" -}}
{{- range $index,$data := .Values.persistence.nfs }}
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: {{ $.Release.Name }}-{{ $.Release.Name }}-{{ $index }}
  namespace: {{ $.Release.Namespace }}
spec:
  {{- if $.Values.persistence.storageClassName }}
  storageClassName: {{ $.Values.persistence.storageClassName }}
  {{- end }}
  accessModes:
    - {{ $.Values.persistence.accessMode }}
  resources:
    requests:
      storage: {{ $.Values.persistence.size }}i
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}-{{ $index }}
---
{{- end }}
{{- end -}}

{{/*
anan nfs PersistentVolume
*/}}
{{- define "anan.persistentvolume.nfs" -}}
{{- range $index,$data := .Values.persistence.nfs }}
apiVersion: v1
kind: PersistentVolume
metadata:
  name: {{ $.Release.Name }}-{{ $index }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if $.Values.persistence.lables }}
  {{- $.Values.persistence.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}-{{ $index }}
  {{- end }}
spec:
  capacity:
    storage: {{ $.Values.persistence.size }}i
  accessModes:
    - {{ $.Values.persistence.accessMode }}
  nfs:
    server: {{ $data.server }}
    {{- if $data.path }}
    path: {{ $data.path }}
    {{- else }}
    path: {{ include "anan.workdir.nfs" . }}{{ $.Release.Name }}{{ $index }}
    {{- end }}
---
{{- end }}
{{- end -}}

{{/*
用于deployment负载均衡或者暴漏端口到主机
*/}}
{{- define "anan.service" -}}
kind: Service
apiVersion: v1
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
  labels:
  {{- if $.Values.service.lables }}
  {{- $.Values.service.lables | nindent 4 }}
  {{- else }}
  {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  {{- end }}
spec:
  ports:
    {{- range $.Values.service.ports }}
    {{- if .nodePort }}
    - name: {{ .name }}
      port: {{ .port }}
      targetPort: {{ .targetPort }}
      {{- if .protocol }}
      protocol: {{ .protocol }}
      {{- end }}
      {{- if eq $.Values.service.type "NodePort" }}
      nodePort: {{ .nodePort }}
      {{- end }}
    {{- end }}
    {{- end }}
  selector:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  type: {{ $.Values.service.type }}
{{- end -}}

{{/*
headless service 用于使用hostname访问pod
*/}}
{{- define "anan.service.headless" -}}
kind: Service
apiVersion: v1
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
  {{- with $.Values.service.clusterIP }}
  clusterIP: {{ . }}
  {{- end }}
  # publishNotReadyAddresses, when set to true, indicates that DNS implementations must publish the notReadyAddresses of subsets for the Endpoints associated with the Service. The default value is false. The primary use case for setting this field is to use a StatefulSet's Headless Service to propagate SRV records for its Pods without respect to their readiness for purpose of peer discovery. This field will replace the service.alpha.kubernetes.io/tolerate-unready-endpoints when that annotation is deprecated and all clients have been converted to use this field.
  # 由于使用DNS访问Pod需Pod和Headless service启动之后才能访问，publishNotReadyAddresses设置成true，防止readinessProbe在服务没启动时找不到DNS
  publishNotReadyAddresses: true
  ports:
    {{- range $.Values.service.ports }}
    - name: {{ .name }}
      port: {{ .port }}
      targetPort: {{ .targetPort }}
      {{- if .protocol }}
      protocol: {{ .protocol }}
      {{- end }}
    {{- end }}
  selector:
    {{- include "anan.lable.name" . | nindent 4 }}: {{ $.Release.Name }}
  type: ClusterIP
{{- end -}}

{{/*
anan deployment模版
*/}}
{{- define "anan.deployment" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  replicas: {{ $.Values.replicaCount }}
  {{- with $.Values.revisionHistoryLimit }}
  revisionHistoryLimit: {{ . }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
  {{- with $.Values.deployment.minReadySeconds }}
  minReadySeconds: {{ . }}
  {{- end }}
  {{- with $.Values.deployment.strategy }}
  strategy:
  {{- toYaml . | nindent 4 }}
  {{- end }}
  template:
    metadata:
      labels:
      {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
    spec:
      {{- if $.Values.affinity }}
      affinity:
      {{- toYaml $.Values.affinity | nindent 8 }}
      {{- else }}
      affinity:
        podAntiAffinity:
          preferredDuringSchedulingIgnoredDuringExecution:
            - weight: 100
              podAffinityTerm:
                labelSelector:
                  matchExpressions:
                    - key: name
                      operator: In
                      values:
                        - {{ $.Release.Name }}
                topologyKey: kubernetes.io/hostname
      {{- end }}
      {{- if or $.Values.role $.Values.clusterRole }}
      serviceAccountName: {{ $.Release.Name }}
      {{- end }}
      {{- with $.Values.terminationGracePeriodSeconds }}
      terminationGracePeriodSeconds: {{ . }}
      {{- end }}
      {{- with .Values.initContainers }}
      initContainers:
      {{- toYaml . | nindent 8 }}
      {{ end }}
      containers:
        - name: {{ $.Release.Name }}
          image: {{ $.Values.image }}
          imagePullPolicy: {{ $.Values.imagePullPolicy }}
          ports:
          {{- range $.Values.service.ports }}
            - name: {{ .name }}
              containerPort: {{ .targetPort }}
              {{- if .protocol }}
              protocol: {{ .protocol }}
              {{- end }}
          {{- end }}
          {{- with $.Values.envFrom }}
          envFrom:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.env }}
          env:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.command }}
          command:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.args }}
          args:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.securityContext }}
          securityContext:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.lifecycle }}
          lifecycle:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.resources }}
          resources:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.livenessProbe }}
          livenessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.readinessProbe }}
          readinessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.volumeMounts }}
          volumeMounts:
          {{- toYaml . | nindent 12 }}
          {{- end }}
      {{- with $.Values.volumes }}
      volumes:
      {{- toYaml . | nindent 8 }}
      {{- end }}
{{- end -}}

{{/*
anan statefulset 模版
*/}}
{{- define "anan.statefulset" -}}
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  serviceName: {{ include "anan.service.headless.name" . }}
  replicas: {{ $.Values.replicaCount }}
  {{- with $.Values.revisionHistoryLimit }}
  revisionHistoryLimit: {{ . }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
  {{- with $.Values.statefulset.updateStrategy }}
  updateStrategy:
  {{- toYaml . | nindent 4 }}
  {{- end }}
  template:
    metadata:
      labels:
      {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
    spec:
      {{- with $.Values.nodeSelector }}
      nodeSelector:
      {{- toYaml . | nindent 8 }}
      {{- end }}
      {{- with $.Values.tolerations }}
      tolerations:
      {{- toYaml . | nindent 8 }}
      {{- end }}
      {{- if $.Values.affinity }}
      affinity:
      {{- toYaml $.Values.affinity | nindent 8 }}
      {{- else }}
      affinity:
        podAntiAffinity:
          preferredDuringSchedulingIgnoredDuringExecution:
            - weight: 100
              podAffinityTerm:
                labelSelector:
                  matchExpressions:
                    - key: name
                      operator: In
                      values:
                        - {{ $.Release.Name }}
                topologyKey: kubernetes.io/hostname
      {{- end }}
      {{- if or $.Values.role $.Values.clusterRole }}
      serviceAccountName: {{ $.Release.Name }}
      {{- end }}
      {{- with $.Values.terminationGracePeriodSeconds }}
      terminationGracePeriodSeconds: {{ . }}
      {{- end }}
      {{- with $.Values.initContainers }}
      initContainers:
      {{- toYaml . | nindent 8 }}
      {{ end }}
      containers:
        - name: {{ $.Release.Name }}
          image: {{ $.Values.image }}
          imagePullPolicy: {{ $.Values.imagePullPolicy }}
          ports:
            {{- range $.Values.service.ports }}
            - name: {{ .name }}
              containerPort: {{ .targetPort }}
              {{- if .protocol }}
              protocol: {{ .protocol }}
              {{- end }}
            {{- end }}
          {{- with $.Values.envFrom }}
          envFrom:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.env }}
          env:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.command }}
          command:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.args }}
          args:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.lifecycle }}
          {{- with $.Values.securityContext }}
          securityContext:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          lifecycle:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.resources }}
          resources:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.livenessProbe }}
          livenessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.readinessProbe }}
          readinessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.volumeMounts }}
          volumeMounts:
          {{- toYaml . | nindent 12 }}
          {{- end }}
      {{- with $.Values.volumes }}
      volumes:
      {{- toYaml . | nindent 8 }}
      {{- end }}
  {{- if $.Values.volumeClaimTemplates }}
  volumeClaimTemplates:
  {{- toYaml . | nindent 4 }}
  {{- else }}
  {{- if $.Values.persistence }}
  {{- if $.Values.persistence.nfs }}
  volumeClaimTemplates:
    - metadata:
        name: {{ .Release.Name }}
        namespace: {{ .Release.Namespace }}
      spec:
        accessModes: [ {{ $.Values.persistence.accessMode }} ]
        storageClassName: {{ $.Values.persistence.storageClassName }}
        resources:
          requests:
            storage: {{ $.Values.persistence.size }}i
  {{- end }}
  {{- end }}
  {{- end }}
{{- end -}}

{{/*
anan configmap configs模式模版
*/}}
{{- define "anan.configmap.conf" -}}
apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ $.Release.Name }}-conf
  namespace: {{ .Release.Namespace }}
data:
{{- range $key, $val := .Values.configmap.confs }}
  {{ $key }}: |-
{{- $val | nindent 4}}
{{- end }}
{{- end -}}

{{/*
anan configmap env模式模版
*/}}
{{- define "anan.configmap.env" -}}
apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ $.Release.Name }}-envs
  namespace: {{ .Release.Namespace }}
data:
{{- range $key, $val := .Values.configmap.envs }}
  {{ $key }}: |-
{{- $val | nindent 4}}
{{- end }}
{{- end -}}

{{/*
anan deployment模版
*/}}
{{- define "anan.daemonset" -}}
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
  {{- with $.Values.daemonset.minReadySeconds }}
  minReadySeconds: {{ . }}
  {{- end }}
  {{- with $.Values.daemonset.updateStrategy }}
  updateStrategy:
  {{- toYaml . | nindent 4 }}
  {{- end }}
  template:
    metadata:
      labels:
      {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
    spec:
      {{- if $.Values.affinity }}
      affinity:
      {{- toYaml $.Values.affinity | nindent 8 }}
      {{- else }}
      affinity:
        podAntiAffinity:
          preferredDuringSchedulingIgnoredDuringExecution:
            - weight: 100
              podAffinityTerm:
                labelSelector:
                  matchExpressions:
                    - key: name
                      operator: In
                      values:
                        - {{ $.Release.Name }}
                topologyKey: kubernetes.io/hostname
      {{- end }}
      {{- if or $.Values.role $.Values.clusterRole }}
      serviceAccountName: {{ $.Release.Name }}
      {{- end }}
      {{- with $.Values.terminationGracePeriodSeconds }}
      terminationGracePeriodSeconds: {{ . }}
      {{- end }}
      {{- with $.Values.initContainers }}
      initContainers:
      {{- toYaml . | nindent 8 }}
      {{ end }}
      containers:
        - name: {{ $.Release.Name }}
          image: {{ $.Values.image }}
          imagePullPolicy: {{ $.Values.imagePullPolicy }}
          ports:
          {{- range $.Values.service.ports }}
            - name: {{ .name }}
              containerPort: {{ .targetPort }}
          {{- end }}
          {{- with $.Values.envFrom }}
          envFrom:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.env }}
          env:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.securityContext }}
          securityContext:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.args }}
          args:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.command }}
          command:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.lifecycle }}
          lifecycle:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.resources }}
          resources:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.livenessProbe }}
          livenessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.readinessProbe }}
          readinessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $.Values.volumeMounts }}
          volumeMounts:
          {{- toYaml . | nindent 12 }}
          {{- end }}
      {{- with $.Values.volumes }}
      volumes:
      {{- toYaml . | nindent 8 }}
      {{- end }}
{{- end -}}
