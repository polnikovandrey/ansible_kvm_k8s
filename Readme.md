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
- `cluster_variant` - вариантов установки `[all-in-one, ha-cluster]`
- `cri_variant` - движок контейнеризации `[container-d, cri-o, docker]`
- `net_variant` - вариант комбинации сетевых плагинов `[flannel_keepalived_haproxy, calico_istio]`
- `libvirt_pool_images` - путь для хранения iso образа и шаблона
- `libvirt_pool_dir` - путь для хранения виртуальных машин
- `vm_net` - название сети для виртуальных машин 
- `ssh_key` - путь к публичному ключу пользователя
- `vm_info` - параметры виртуальных машин

## Запуск развертывания
`ansible-playbook -K ./setup_k8s.yaml -i ./inventory --extra-vars "@my_vars.yml"`

## Удаление стенда
`ansible-playbook -K ./remove_stand.yml`

## Проверка сетевых настроек
```
Убедимся, что все сетевые настройки сделаны правильно.
Для этого на каждом узле по очереди выполним следующие тестовые команды:

# Проверка доступности шлюза по умолчанию
ping 172.30.0.2 -c 1

# Проверка доступности node1
ping 172.30.0.201 -c 1
ping node1.internal -c 1

# Проверка доступности node2
ping 172.30.0.202 -c 1
ping node2.internal -c 1

# Проверка доступности node3
ping 172.30.0.203 -c 1
ping node3.internal -c 1

# Проверка доступности node4
ping 172.30.0.204 -c 1
ping node4.internal -c 1

# Проверка доступности node5
ping 172.30.0.205 -c 1
ping node5.internal -c 1

# Проверка «видимости» Интернета
ping 8.8.8.8 -c 1
```
