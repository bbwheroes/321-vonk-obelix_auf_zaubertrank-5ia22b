#!/bin/bash
set -e

# Start each service in the background
java -jar obelix-quarry-impl.jar &
java -jar obelix-basket-impl.jar &
java -jar obelix-webshop.jar &

# Wait for all background processes
wait
