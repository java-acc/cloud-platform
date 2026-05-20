{{/*
Common labels.
*/}}
{{- define "platform.labels" -}}
app.kubernetes.io/managed-by: Helm
app.kubernetes.io/part-of: {{ .Release.Name }}
{{- end -}}

{{/*
Selector labels for a given service key.
*/}}
{{- define "platform.selectorLabels" -}}
app: {{ .name }}
{{- end -}}
