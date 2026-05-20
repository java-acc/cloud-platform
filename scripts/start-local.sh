#!/usr/bin/env bash
# Bring up the local stack: Nacos + MySQL + Redis + Sentinel dashboard.
set -euo pipefail

cd "$(dirname "$0")/.."

docker compose -f deploy/docker/docker-compose.yml up -d

echo
echo "Nacos:             http://localhost:8848/nacos      (nacos/nacos)"
echo "Sentinel:          http://localhost:8858            (sentinel/sentinel)"
echo "MySQL:             localhost:3306                   (root/root)"
echo "Redis:             localhost:6379"
