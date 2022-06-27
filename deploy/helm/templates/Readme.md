# anan-templates使用介绍

## 引入chat
    例如：redis引用anan-templates的方法如下：
```kubernetes helm
apiVersion: v2
description: A Redis Helm chart for Kubernetes
name: redis
version: x.y.z
dependencies:
  - name: anan-templates
    version: 0.0.3
    repository: https://fosin.github.io/anan-cloud

```
## 引入K8S组件模版
    在templates目录下创建一个from-tpl.yaml文件，文件内容如下：
```gotemplate
{{/*引入ConfigMap模版*/}}
{{- if .Values.configmap }}
{{ include "anan.configmap" . }}
{{- end }}

{{/*引入secret模版*/}}
{{- if .Values.secret }}
---
{{ include "anan.secret" . }}
{{- end }}

{{/*引入Service模版*/}}
{{ if .Values.service }}
{{- if .Values.statefulset }}
---
{{ include "anan.service.headless" . }}
{{ end }}
---
{{ include "anan.service" . }}
{{ end }}

{{/*引入deployment模版*/}}
{{- if .Values.deployment }}
---
{{ include "anan.deployment" . }}
{{- end }}

{{/*引入statefulset模版*/}}
{{- if .Values.statefulset }}
---
{{ include "anan.statefulset" . }}
{{- end }}

{{/*引入daemonset模版*/}}
{{- if .Values.daemonset }}
---
{{ include "anan.daemonset" . }}
{{- end }}

{{/*引入cluster role模版*/}}
{{- if .Values.cluster }}
---
{{ include "anan.cluster.role" . }}
---
{{ include "anan.cluster.rolebinding" . }}
{{- end }}

{{/*引入role模版*/}}
{{- if .Values.role }}
---
{{ include "anan.role" . }}
---
{{ include "anan.rolebinding" . }}
{{- end }}

{{/*引入serviceaccount模版*/}}
{{- if or .Values.cluster .Values.role }}
---
{{ include "anan.serviceaccount" . }}
{{- end }}

{{/*引入pv、pvc模版*/}}
{{- if .Values.persistence }}
---
{{ include "anan.pv" . }}
---
{{ include "anan.pvc" . }}
{{- end }}

```
## 编写chart的values.yaml文件
详细介绍 [点这里value.yaml](anan-templates/values.yaml)

## 原理介绍
