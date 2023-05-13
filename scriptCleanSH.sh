containerRunning=$(docker ps -a | grep cms-springboot)
if [ -z "$containerRunning" ]; then
        echo "Container Absent"
else
        echo "Stopping Container"
        docker stop cms-springboot
        docker rm cms-springboot
fi
