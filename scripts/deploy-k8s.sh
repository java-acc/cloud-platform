#!/usr/bin/env bash
# Apply all K8S manifests under deploy/k8s/.
set -euo pipefail

cd "$(dirname "$0")/.."

kubectl apply -f deploy/k8s/namespace.yaml
kubectl apply -f deploy/k8s/

echo
echo "✓ kubectl apply done in namespace byc-cloud-platform."
