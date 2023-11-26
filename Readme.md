# Разворачивание стенда K8s на KVM
## Зависимости
### KVM
https://linuxgenie.net/how-to-install-kvm-on-ubuntu-22-04/

## Основные варианты развертывания стенда
### Состав кластера
- `all-in-one` - аналог minikube, всё в одном сервере
- `ha-cluster` - кластер из 5 машин (3 manager + 2 worker)
### Используемый движок контейнеризации
- `docker`
- `container-d`
- `cri-o`
## Определение параметров развертывания в my_vars.yml
- `variant` - вариантов установки `[all-in-one, ha-cluster]`
- `engine` - движок контейнеризации `[container-d, cri-o, docker]`
- `libvirt_pool_images` - путь для хранения iso образа и шаблона
- `libvirt_pool_dir` - путь для хранения виртуальных машин
- `vm_net` - название сети для виртуальных машин 
- `ssh_key` - путь к публичному ключу пользователя
- `vm_info` - параметры виртуальных машин
## Запуск развертывания
`ansible-playbook -K ./setup_k8s.yaml -i ./inventory --extra-vars "@my_vars.yml"`
## Удаление стенда
`ansible-playbook -K ./remove_stand.yml`