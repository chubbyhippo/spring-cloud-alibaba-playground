#!/bin/sh
echo "Nacos auto config started"
exampleConfig=$(cat ../config/nacos-config-example.properties)
groupId="GROUP"
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=datasource-config.yaml&group=${groupId}&content=${exampleConfig}"
echo "Nacos config pushed successfully finished"
