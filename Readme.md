# Разворачивание стенда K8s на KVM
## Зависимости

### KVM
[How to Install KVM on Ubuntu 22.04](https://linuxgenie.net/how-to-install-kvm-on-ubuntu-22-04/)

### K8s
[Гайд для новичков по установке Kubernetes](https://habr.com/ru/articles/725640/)

[Автоматизация развертывания стенда Kubernetes](https://habr.com/ru/articles/751582/)

[Diving Deep into Kubernetes Networking](https://more.suse.com/rs/937-DCH-261/images/Diving-Deep-Into-Kubernetes-Networking.pdf)

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