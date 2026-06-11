#!/usr/bin/env bash
# Build the whole workspace.
set -euo pipefail

cd "$(dirname "$0")/.."

mvn -B -ntp -DskipTests clean package "$@"

echo
echo "✓ build done. JARs are under each module's target/ directory."
