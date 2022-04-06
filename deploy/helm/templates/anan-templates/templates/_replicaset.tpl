
{{/*
anan replicaset 模版(包括：statefulset、deployment、daemonset)
*/}}
{{- define "anan.replicaset" -}}
{{- $labels := "{}" }}
{{- if $.Values.statefulset }}
kind: "StatefulSet"
apiVersion: {{ $.Values.statefulset.apiVersion | default "apps/v1" }}
{{- $labels := $.Values.statefulset.labels }}
{{- else }}
  {{- if $.Values.deployment }}
kind: "Deployment"
apiVersion: {{ $.Values.deployment.apiVersion | default "apps/v1" }}
{{- $labels := $.Values.deployment.labels }}
  {{- else }}
kind: "DaemonSet"
apiVersion: {{ $.Values.daemonset.apiVersion | default "apps/v1" }}
{{- $labels := $.Values.daemonset.labels }}
  {{- end }}
{{- end }}
metadata:
  name: {{ $.Release.Name }}
  namespace: {{ $.Release.Namespace }}
spec:
  {{- if $.Values.statefulset }}
  serviceName: {{ include "anan.service.headless.name" . }}
  podManagementPolicy: {{ $.Values.statefulset.podManagementPolicy | default "Parallel" }}
  {{- end }}
  {{- with $.Values.revisionHistoryLimit }}
  revisionHistoryLimit: {{ . }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "anan.lable.name" . | nindent 6 }}: {{ $.Release.Name }}
  {{- if $.Values.statefulset }}
    {{- with $.Values.statefulset.updateStrategy }}
  updateStrategy:
    {{- toYaml . | nindent 4 }}
    {{- end }}
    {{- with $.Values.statefulset.replicaCount }}
  replicas: {{ . }}
    {{- end }}
  {{- else }}
    {{- if $.Values.deployment }}
      {{- with $.Values.deployment.replicaCount }}
  replicas: {{ . }}
      {{- end }}
      {{- with $.Values.deployment.strategy }}
  strategy:
      {{- toYaml . | nindent 4 }}
      {{- end }}
      {{- with $.Values.deployment.minReadySeconds }}
  minReadySeconds: {{ . }}
      {{- end }}
    {{- else }}
      {{- with $.Values.daemonset.updateStrategy }}
  updateStrategy:
      {{- toYaml . | nindent 4 }}
      {{- end }}
      {{- with $.Values.daemonset.minReadySeconds }}
  minReadySeconds: {{ . }}
      {{- end }}
  {{- end }}
  {{- end }}
  template:
    metadata:
      labels:
      {{- include "anan.lable.name" . | nindent 8 }}: {{ $.Release.Name }}
      {{- if $.Values.statefulset }}
        {{- with $.Values.statefulset.labels }}
        {{- toYaml . | nindent 8 }}
        {{ end }}
        {{- with $.Values.statefulset.annotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
        {{ end }}
      {{- else }}
        {{- if $.Values.deployment }}
            {{- with $.Values.deployment.labels }}
            {{- toYaml . | nindent 8 }}
            {{ end }}
        {{- with $.Values.deployment.annotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
        {{ end }}
        {{- else }}
            {{- with $.Values.daemonset.labels }}
            {{- toYaml . | nindent 8 }}
            {{ end }}
        {{- with $.Values.daemonset.annotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
        {{ end }}
        {{- end }}
    {{- end }}
    spec:
      {{- with $.Values.tolerations }}
      tolerations:
      {{- toYaml . | nindent 8 }}
      {{- end }}
      {{- if $.Values.affinity }}
      affinity:
      {{- toYaml $.Values.affinity | nindent 8 }}
      {{- else if or $.Values.podAntiAffinity $.Values.podAffinity $.Values.nodeAntiAffinity $.Values.nodeAffinity }}
      affinity:
      {{- with $.Values.podAntiAffinity }}
        podAntiAffinity:
          {{ . }}:
            - labelSelector:
                matchExpressions:
                - key: {{ include "anan.lable.name" . }}
                  operator: In
                  values:
                  - {{ $.Release.Name }}
              topologyKey: "kubernetes.io/hostname"
      {{- end }}
      {{- with $.Values.podAffinity }}
        podAffinity:
          {{ . }}:
            - labelSelector:
                matchExpressions:
                - key: {{ include "anan.lable.name" . }}
                  operator: In
                  values:
                  - {{ $.Release.Name }}
              topologyKey: "kubernetes.io/hostname"
      {{- end }}
      {{- with $.Values.nodeAntiAffinity }}
      {{- if and $.Values.nodeAntiAffinity.duringScheduling $.Values.nodeAntiAffinity.nodeNamePrefix }}
        nodeAntiAffinity:
          {{ $.Values.nodeAntiAffinity.duringScheduling }}:
            - labelSelector:
                matchExpressions:
                - key: kubernetes.io/hostname
                  operator: In
                  values:
                  - {{ $.Values.nodeAntiAffinity.nodeNamePrefix }}
      {{- end }}
      {{- end }}
      {{- with $.Values.nodeAffinity }}
      {{- if and $.Values.nodeAffinity.duringScheduling $.Values.nodeAffinity.nodeNamePrefix }}
        nodeAffinity:
          {{ $.Values.nodeAffinity.duringScheduling }}:
            - labelSelector:
                matchExpressions:
                - key: kubernetes.io/hostname
                  operator: In
                  values:
                  - {{ $.Values.nodeAffinity.nodeNamePrefix }}
      {{- end }}
      {{- end }}
      {{- end }}
      {{- if $.Values.cluster }}
      serviceAccountName: {{ $.Values.cluster.serviceAccountName | default $.Release.Name }}
      {{- else }}
      {{- if $.Values.role }}
      serviceAccountName: {{ $.Values.role.serviceAccountName | default $.Release.Name }}
      {{- end }}
      {{- end }}
      {{- with $.Values.priorityClassName }}
      priorityClassName: {{ . }}
      {{- end }}
      {{- with $.Values.hostNetwork }}
      hostNetwork: {{ . }}
      {{- end }}
      {{- with $.Values.hostIPC }}
      hostIPC: {{ . }}
      {{- end }}
      {{- with $.Values.hostPID }}
      hostPID: {{ . }}
      {{- end }}
      {{- with $.Values.hostname }}
      hostname: {{ . }}
      {{- end }}
      {{- with $.Values.nodeName }}
      nodeName: {{ . }}
      {{- end }}
      {{- with $.Values.nodeSelector }}
      nodeSelector:
      {{- toYaml . | nindent 8 }}
      {{- end }}
      {{- with $.Values.restartPolicy }}
      restartPolicy: {{ . }}
      {{- end }}
      {{- with $.Values.terminationGracePeriodSeconds }}
      terminationGracePeriodSeconds: {{ . }}
      {{- end }}
      {{- with $.Values.initContainers }}
      initContainers:
      {{- toYaml . | nindent 8 }}
      {{ end }}
      containers:
      {{- range $z,$dataz := $.Values.containers }}
        - name: {{ $dataz.name | default $.Release.Name }}
          image: {{ $dataz.image }}
          imagePullPolicy: {{ $dataz.imagePullPolicy }}
          {{- if $dataz.ports }}
          ports:
          {{- toYaml $dataz.ports | nindent 12 }}
          {{- else }}
          {{- if $.Values.service }}
          {{- if $.Values.service.ports }}
          ports:
          {{- range $.Values.service.ports }}
            - name: {{ .name }}
              containerPort: {{ .targetPort | default .port }}
              {{- with .protocol }}
              protocol: {{ . }}
              {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- with $dataz.workingDir }}
          workingDir: {{ . }}
          {{- end }}
          {{- with $dataz.envFrom }}
          envFrom:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.env }}
          env:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.command }}
          command:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.args }}
          args:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.securityContext }}
          securityContext:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.lifecycle }}
          lifecycle:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.resources }}
          resources:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.livenessProbe }}
          livenessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.readinessProbe }}
          readinessProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.startupProbe }}
          startupProbe:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- with $dataz.volumeDevices }}
          volumeDevices:
          {{- toYaml . | nindent 12 }}
          {{- end }}
          {{- if $dataz.volumeMounts }}
          volumeMounts:
          {{- toYaml $dataz.volumeMounts | nindent 12 }}
          {{- else }}
          {{- if or $.Values.persistence $.Values.configmap $.Values.hostPath $.Values.emptyDir $.Values.secret }}
          volumeMounts:
          {{- range $x,$datax := $.Values.persistence }}
          {{- if $datax.name }}
            - name: {{ $datax.name }}
          {{- else }}
            - name: {{ $.Release.Name }}-{{ $x }}
          {{- end }}
              mountPath: {{ $datax.mountPath }}
          {{- end }}
          {{- if $.Values.configmap }}
          {{- range $index, $file := $.Values.configmap.files }}
          {{- if $file.mountPath }}
          {{- if eq (clean $file.mountPath) $file.mountPath }}
            - name: {{ $.Release.Name }}-{{ $index }}
              mountPath: {{ $file.mountPath }}
              {{- with $file.readOnly }}
              readOnly: {{ . }}
              {{- end }}
          {{- else }}
          {{- range $key, $val := $file.mountFiles }}
            - name: {{ $.Release.Name }}-{{ $index }}
              mountPath: {{ $file.mountPath }}{{ $key }}
              {{- with $file.readOnly }}
              readOnly: {{ . }}
              {{- end }}
              subPath: {{ $key }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- if $.Values.secret }}
          {{- range $index, $file := $.Values.secret.files }}
          {{- if $file.mountPath }}
          {{- if eq (clean $file.mountPath) $file.mountPath }}
            - name: {{ $.Release.Name }}-secret-{{ $index }}
              mountPath: {{ $file.mountPath }}
              {{- with $file.readOnly }}
              readOnly: {{ . }}
              {{- end }}
          {{- else }}
          {{- range $key, $val := $file.mountFiles }}
            - name: {{ $.Release.Name }}-secret-{{ $index }}
              mountPath: {{ $file.mountPath }}{{ $key }}
              {{- with $file.readOnly }}
              readOnly: {{ . }}
              {{- end }}
              subPath: {{ $key }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- end }}
          {{- range $index, $hostPath := $.Values.hostPath }}
            - name: {{ $.Release.Name }}-hostpath-{{ $index }}
              mountPath: {{ $hostPath.mountPath }}
              {{- with $hostPath.readOnly }}
              readOnly: {{ . }}
              {{- end }}
              {{- with $hostPath.subPath }}
              subPath: {{ . }}
              {{- end }}
          {{- end }}
          {{- range $index, $emptyDir := $.Values.emptyDir }}
            - name: {{ $.Release.Name }}-emptydir-{{ $index }}
              mountPath: {{ $emptyDir }}
          {{- end }}
          {{- end }}
          {{- end }}
      {{- end }}
      {{- if $.Values.volumes }}
      volumes:
      {{- toYaml $.Values.volumes | nindent 8 }}
      {{- else }}
      {{- if or $.Values.configmap $.Values.hostPath $.Values.emptyDir $.Values.secret }}
      volumes:
      {{- if $.Values.configmap }}
      {{- $configmapName := $.Values.configmap.existConfigMapName | default (include "anan.configmap.name" .) }}
      {{- range $index, $file := $.Values.configmap.files }}
        {{- if $file.mountPath }}
        - name: {{ $.Release.Name }}-{{ $index }}
          configMap:
            name: {{ $configmapName }}
            {{- with $file.defaultMode }}
            defaultMode: {{ . }}
            {{- end }}
            items:
        {{- range $key, $val := $file.mountFiles }}
              - key: {{ $key }}
                path: {{ $key }}
        {{- end }}
        {{- end }}
      {{- end }}
      {{- end }}
      {{- if $.Values.secret }}
      {{- $secretName := $.Values.secret.existSecretName | default (include "anan.secret.name" .) }}
      {{- range $index, $file := $.Values.secret.files }}
        {{- if $file.mountPath }}
       - name: {{ $.Release.Name }}-secret-{{ $index }}
          secret:
            name: {{ $secretName }}
            {{- with $file.defaultMode }}
            defaultMode: {{ . }}
            {{- end }}
            items:
        {{- range $key, $val := $file.mountFiles }}
              - key: {{ $key }}
                path: {{ $key }}
        {{- end }}
        {{- end }}
      {{- end }}
      {{- end }}
      {{- range $index, $hostPath := $.Values.hostPath }}
        - name: {{ $.Release.Name }}-hostpath-{{ $index }}
          hostPath:
            path: {{ $hostPath.path }}
            {{- with $hostPath.type }}
            type: {{ . }}
            {{- end }}
      {{- end }}
      {{- range $index, $emptyDir := $.Values.emptyDir }}
        - name: {{ $.Release.Name }}-emptydir-{{ $index }}
          emptyDir: {}
      {{- end }}
      {{- end }}
      {{- end }}
  {{- if $.Values.statefulset }}
  {{- if $.Values.volumeClaimTemplates }}
  volumeClaimTemplates:
  {{- toYaml $.Values.volumeClaimTemplates | nindent 4 }}
  {{- else }}
  {{- if $.Values.persistence }}
  volumeClaimTemplates:
  {{- range $x,$datax := .Values.persistence }}
    - metadata:
        {{- if $datax.name }}
        name: {{ $datax.name }}
        {{- else }}
        name: {{ $.Release.Name }}-{{ $x }}
        {{- end }}
        namespace: {{ $.Release.Namespace }}
      spec:
        accessModes: [ {{ $datax.accessMode }} ]
        {{- with $datax.storageClassName }}
        storageClassName: {{ toYaml . }}
        {{- end }}
        resources:
          requests:
            storage: {{ $datax.size }}i
  {{- end }}
  {{- end }}
  {{- end }}
  {{- end }}
{{- end -}}
