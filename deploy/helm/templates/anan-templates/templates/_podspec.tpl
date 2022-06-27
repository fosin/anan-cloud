{{/*
anan podspec 模版
*/}}
{{- define "anan.podspec" -}}
{{- $hasMountPath1 := (hasKey (first (.Values.configmap | default list)) "mountPath") }}
{{- $hasMountPath2 := (hasKey (first ($.Values.secret | default list)) "mountPath") }}
{{- $hasMountPath3 := (hasKey (first ($.Values.hostPath | default list)) "mountPath") }}
{{- $hasMountPath5 := (hasKey (first ($.Values.persistence | default list)) "mountPath") }}
{{- $hasMountPath4 := (gt (len ($.Values.emptyDir | default list)) 0) }}
{{- $hasMountPath := or $hasMountPath1 $hasMountPath2 $hasMountPath3 $hasMountPath4 $hasMountPath5 }}
{{- with $.Values.tolerations }}
tolerations:
{{- toYaml . | nindent 2 }}
{{- end }}
{{- if $.Values.affinity }}
affinity:
{{- toYaml $.Values.affinity | nindent 2 }}
{{- else if or $.Values.podAntiAffinity $.Values.podAffinity $.Values.nodeAntiAffinity $.Values.nodeAffinity }}
affinity:
{{- $releaseName := $.Release.Name }}
{{- with $.Values.podAntiAffinity }}
  podAntiAffinity:
  {{- if contains .duringScheduling "required" }}
    requiredDuringSchedulingIgnoredDuringExecution:
      - labelSelector:
          matchExpressions:
          - key: {{ .key | default (include "anan.lable.name" .) }}
            operator: {{ .operator | default "In" }}
            values:
              - {{ .value | default $releaseName }}
        topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
  {{- else }}
    preferredDuringSchedulingIgnoredDuringExecution:
      - weight: 100
        podAffinityTerm:
          labelSelector:
            matchExpressions:
            - key: {{ .key | default (include "anan.lable.name" .) }}
              operator: {{ .operator | default "In" }}
              values:
                - {{ .value | default $releaseName }}
          topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
  {{- end }}
{{- end }}
{{- with $.Values.podAffinity }}
  podAffinity:
  {{- if contains .duringScheduling "required" }}
    requiredDuringSchedulingIgnoredDuringExecution:
      - labelSelector:
          matchExpressions:
          - key: {{ .key | default (include "anan.lable.name" .) }}
            operator: {{ .operator | default "In" }}
            values:
              - {{ .value | default $releaseName }}
        topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
  {{- else }}
    preferredDuringSchedulingIgnoredDuringExecution:
      - weight: 100
        podAffinityTerm:
          labelSelector:
            matchExpressions:
            - key: {{ .key | default (include "anan.lable.name" .) }}
              operator: {{ .operator | default "In" }}
              values:
                - {{ .value | default $releaseName }}
          topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
  {{- end }}
{{- end }}
{{- with $.Values.nodeAntiAffinity }}
  nodeAntiAffinity:
  {{- (include "anan.podspec.nodeAffinity" .) | nindent 4 }}
{{- end }}
{{- with $.Values.nodeAffinity }}
  nodeAffinity:
  {{- (include "anan.podspec.nodeAffinity" .) | nindent 4 }}
{{- end }}
{{- end }}
{{- if $.Values.cluster }}
serviceAccountName: {{ $.Values.cluster.serviceAccountName | default $.Release.Name }}
{{- else if $.Values.role }}
serviceAccountName: {{ $.Values.role.serviceAccountName | default $.Release.Name }}
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
{{- toYaml . | nindent 2 }}
{{- end }}
{{- with $.Values.restartPolicy }}
restartPolicy: {{ . }}
{{- end }}
{{- with $.Values.terminationGracePeriodSeconds }}
terminationGracePeriodSeconds: {{ . }}
{{- end }}
{{- with $.Values.initContainers }}
initContainers:
{{- toYaml . | nindent 2 }}
{{- end }}
containers:
{{- range $z,$dataz := $.Values.containers }}
  - name: {{ $dataz.name | default $.Release.Name }}
    image: {{ $dataz.image }}
    imagePullPolicy: {{ $dataz.imagePullPolicy }}
    {{- if $dataz.ports }}
    ports:
    {{- toYaml $dataz.ports | nindent 6 }}
    {{- else if $.Values.service }}
    ports:
    {{- range $.Values.service.ports }}
      - name: {{ .name }}
        containerPort: {{ .targetPort | default .port }}
        {{- with .protocol }}
        protocol: {{ . }}
        {{- end }}
    {{- end }}
    {{- end }}
    {{- with $dataz.workingDir }}
    workingDir: {{ . }}
    {{- end }}
    {{- with $dataz.envFrom }}
    envFrom:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.env }}
    env:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.command }}
    command:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.args }}
    args:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.securityContext }}
    securityContext:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.lifecycle }}
    lifecycle:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.resources }}
    resources:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.livenessProbe }}
    livenessProbe:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.readinessProbe }}
    readinessProbe:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.startupProbe }}
    startupProbe:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- with $dataz.volumeDevices }}
    volumeDevices:
    {{- toYaml . | nindent 6 }}
    {{- end }}
    {{- if $dataz.volumeMounts }}
    volumeMounts:
    {{- toYaml $dataz.volumeMounts | nindent 6 }}
    {{- else if $hasMountPath }}
    volumeMounts:
    {{- if $.Values.statefulset }}
    {{- range $x,$datax := $.Values.persistence }}
      - name: {{ default (print $.Release.Name "-" $x) $datax.name }}
        mountPath: {{ $datax.mountPath }}
    {{- end }}
    {{- else }}
    {{- range $index, $pvc := $.Values.persistence }}
      - name: {{ $pvc.name }}-{{ $.Release.Name }}-{{ $index }}
        mountPath: {{ $pvc.mountPath }}
    {{- end }}
    {{- end }}
    {{- range $cmi,$cm := $.Values.configmap }}
    {{- if $cm.mountPath }}
    {{- if and (ne $cm.mountPath "/") (eq (clean $cm.mountPath) $cm.mountPath) }}
      - name: {{ $.Release.Name }}-{{ $cmi }}
        mountPath: {{ $cm.mountPath }}
        {{- with $cm.readOnly }}
        readOnly: {{ . }}
        {{- end }}
    {{- else }}
      {{- $dirFiles := dict }}
      {{- range $diri,$dir := $cm.fromDirs }}
        {{- range $path, $_ := $.Files.Glob $dir }}
          {{- $dirFiles := (set $dirFiles (base $path) $path) }}
        {{- end }}
      {{- end }}
      {{- $allFiles := (mergeOverwrite ($cm.fromFiles | default dict) ($cm.fromTemps | default dict) $dirFiles) }}
      {{- range $key,$val := $allFiles }}
      - name: {{ $.Release.Name }}-{{ $cmi }}
        mountPath: {{ $cm.mountPath }}{{ $key }}
        {{- with $cm.readOnly }}
        readOnly: {{ . }}
        {{- end }}
        subPath: {{ $key }}
      {{- end }}
    {{- end }}
    {{- end }}
    {{- end }}
    {{- range $secrcti,$secrct := $.Values.secret }}
    {{- if $secrct.mountPath }}
    {{- if and (ne $secrct.mountPath "/") (eq (clean $secrct.mountPath) $secrct.mountPath) }}
      - name: {{ $.Release.Name }}-secret-{{ $secrcti }}
        mountPath: {{ $secrct.mountPath }}
        {{- with $secrct.readOnly }}
        readOnly: {{ . }}
        {{- end }}
    {{- else }}
      {{- $dirFiles := dict }}
      {{- range $index,$dir := $secrct.fromDirs }}
        {{- range $path, $_ := $.Files.Glob $dir }}
          {{- $dirFiles := (set $dirFiles (base $path) $path) }}
        {{- end }}
      {{- end }}
      {{- $allFiles := (mergeOverwrite ($secrct.fromFiles | default dict) ($secrct.fromTemps | default dict) $dirFiles) }}
      {{- range $key,$val := $allFiles }}
      - name: {{ $.Release.Name }}-secret-{{ $secrcti }}
        mountPath: {{ $secrct.mountPath }}{{ $key }}
        {{- with $secrct.readOnly }}
        readOnly: {{ . }}
        {{- end }}
        subPath: {{ $key }}
      {{- end }}
    {{- end }}
    {{- end }}
    {{- end }}
    {{- range $index,$hostPath := $.Values.hostPath }}
      - name: {{ $.Release.Name }}-hostpath-{{ $index }}
        mountPath: {{ $hostPath.mountPath }}
        {{- with $hostPath.readOnly }}
        readOnly: {{ . }}
        {{- end }}
        {{- with $hostPath.subPath }}
        subPath: {{ . }}
        {{- end }}
    {{- end }}
    {{- range $index,$emptyDir := $.Values.emptyDir }}
      - name: {{ $.Release.Name }}-emptydir-{{ $index }}
        mountPath: {{ $emptyDir }}
    {{- end }}
    {{- end }}
{{- end }}
{{- if $.Values.volumes }}
volumes:
{{- toYaml $.Values.volumes | nindent 2 }}
{{- else if $hasMountPath }}
volumes:
{{- $configmapName := (include "anan.configmap.name" .) }}
{{- range $cmi,$cm := $.Values.configmap }}
  {{- if $cm.mountPath }}
  - name: {{ $.Release.Name }}-{{ $cmi }}
    configMap:
      name: {{ coalesce $cm.existName $cm.name $configmapName }}
      {{- with $cm.defaultMode }}
      defaultMode: {{ . }}
      {{- end }}
      items:
        {{- $dirFiles := dict }}
        {{- range $index, $dir := $cm.fromDirs }}
          {{- range $path, $_ := $.Files.Glob $dir }}
            {{- $dirFiles := (set $dirFiles (base $path) $path) }}
          {{- end }}
        {{- end }}
        {{- $allFiles := (mergeOverwrite ($cm.fromFiles | default dict) ($cm.fromTemps | default dict) $dirFiles) }}
        {{- range $key, $val := $allFiles }}
        - key: {{ $key }}
          path: {{ $key }}
        {{- end }}
  {{- end }}
{{- end }}
{{- $secretName := (include "anan.secret.name" .) }}
{{- range $index,$secrct := $.Values.secret }}
  {{- if $secrct.mountPath }}
 - name: {{ $.Release.Name }}-secret-{{ $index }}
    secret:
      name: {{ coalesce $secrct.existName $secrct.name $secretName }}
      {{- with $secrct.defaultMode }}
      defaultMode: {{ . }}
      {{- end }}
      items:
        {{- $dirFiles := dict }}
        {{- range $index,$dir := $secrct.fromDirs }}
          {{- range $path, $_ := $.Files.Glob $dir }}
            {{- $dirFiles := (set $dirFiles (base $path) $path) }}
          {{- end }}
        {{- end }}
        {{- $allFiles := (mergeOverwrite ($secrct.fromFiles | default dict) ($secrct.fromTemps | default dict) $dirFiles) }}
        {{- range $key,$val := $allFiles }}
        - key: {{ $key }}
          path: {{ $key }}
        {{- end }}
  {{- end }}
{{- end }}
{{- range $index,$hostPath := $.Values.hostPath }}
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
{{- if not $.Values.statefulset }}
{{- range $index, $pvc := $.Values.persistence }}
  - name: {{ $pvc.name }}-{{ $.Release.Name }}-{{ $index }}
    persistentVolumeClaim:
      claimName: {{ $pvc.name }}-{{ $.Release.Name }}-{{ $index }}
{{- end }}
{{- end }}
{{- end }}
{{- end -}}

{{- define "anan.podspec.nodeAffinity" -}}
{{- if contains .duringScheduling "required" }}
requiredDuringSchedulingIgnoredDuringExecution:
  nodeSelectorTerms:
  - matchExpressions:
    - key: {{ .key | default "kubernetes.io/hostname" }}
      operator: {{ .operator | default "In" }}
      values:
        - {{ required "NodeAffinity must have a value filed!" .value }}
{{- else }}
preferredDuringSchedulingIgnoredDuringExecution:
  - weight: 100
    preference:
      matchExpressions:
      - key: {{ .key | default "kubernetes.io/hostname" }}
        operator: {{ .operator | default "In" }}
        values:
          - {{ required "NodeAffinity must have a value filed!" .value }}
{{- end }}
{{- end -}}

{{- define "anan.podspec.podAffinity" -}}
{{- $releaseName := $.Release.Name }}
{{- if contains .duringScheduling "required" }}
requiredDuringSchedulingIgnoredDuringExecution:
  - labelSelector:
      matchExpressions:
      - key: {{ .key | default (include "anan.lable.name" .) }}
        operator: {{ .operator | default "In" }}
        values:
          - {{ .value | default $releaseName }}
    topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
{{- else }}
preferredDuringSchedulingIgnoredDuringExecution:
  - weight: 100
    podAffinityTerm:
      labelSelector:
        matchExpressions:
        - key: {{ .key | default (include "anan.lable.name" .) }}
          operator: {{ .operator | default "In" }}
          values:
            - {{ .value | default $releaseName }}
      topologyKey: {{ .topologyKey | default "kubernetes.io/hostname" }}
{{- end }}
{{- end -}}
