#!/bin/bash

#Configurando o Snort em Sistemas Debian

echo "Configurando o Snort em Sistemas Debian"

echo "Atualizando os repositorios e os Pacotes..."
sudo apt-get update
sudo apt-get upgrade -y
sudo reboot

echo "Instalando as dependencias..."
sudo apt install openssh-server ethtool build-essential libpcap-dev libpcre3-dev libdumbnet-dev bison flex zlib1g-dev liblzma-dev openssl libssl-dev libnghttp2-dev

echo "Realizando o primeiro download do DAQ(Data Aquisition) na pagina oficial do Snort..."
wget -c https://www.snort.org/downloads/snort/daq-2.0.7.tar.gz

echo "Descompactando o arquivo..."
tar zxvf daq-2.0.7.tar.gz

echo "Entrando no diretorio e realizando as configuracoes..."
cd daq-2.0.7
./configure && make && sudo make install

echo "Evitar que pacotes sejam truncados em 1518 bytes..."
sudo vi /etc/network/interfaces
post-up ethtool -K eth0 gro off
post-up ethtool -K eth0 lro off
post-up ethtool -K interface gro / lro off.

echo "Saindo do diretorio..."
echo "Realizando o download e instalando o Snort..."
wget -c https://www.snort.org/downloads/snort/snort-2.9.20.tar.gz

echo "Descompactando o arquivo..."
tar xvzf snort-2.9.20.tar.gz
echo "Entrando na pasta criada..."
cd snort-2.9.20
echo "Realizando a configuração..."
./configure --enable-sourcefire --enable-sourcefire
make
make install
sudo ldconfig
sudo ln s /usr/local/bin/snort /usr/sbin/snort
echo "Validando a instalacao..."
sudo /usr/sbin/snort-V

echo "Criando pastas e arquivos de configuracao..."

mkdir /etc/snort
mkdir /etc/snort/preproc_rules
mkdir /etc/snort/rules
mkdir /var/log/snort
mkdir /usr/local/lib/snort_dynamicrules
touch /etc/snort/rules/white_list.rules
touch /etc/snort/rules/black_list.rules
touch /etc/snort/rules/local.rules

echo "Configurando os modos de permissao..."
chmod -R 5775 /etc/snort/
chmod -R 5775 /var/log/snort/
chmod -R 5775 /usr/local/lib/snort
sudo chmod -R 5775 /var/log/snort/archived_logs
chmod -R 5775 /usr/local/lib/snort_dynamicrules/

echo "Mudando os donos dos arquivos..."
sudo chown -R snort:snort /etc/snort
sudo chown -R snort:snort /var/log/snort
sudo chown -R snort:snort /usr/local/lib/snort_dynamicrules

echo "Copiar e mover os aquivos de configuracao para /etc/snort/..."
echo "Arquivo1: attribute table.dtd: permite ao Snort utilizar informações externas para determinar políticas e protocolos;..."
echo "Arquivo2: classification.config: descreve os tipos de classes de ataques compreendidos pelo Snort;..."
echo "Arquivo3: file_magic.conf: descreve regras para identificar o tipo de cada arquivo;..."
echo "Arquivo4: gen-msg.map: deixa o Snort ciente de qual pré-processador é acionado por cada regra;..."
echo "Arquivo5: reference.config: armazena URLs inseridas nas regras para fornecer informações sobre alertas;..."
echo "Arquivo6: snort.conf: é o próprio arquivo de configuração do Snort;..."
echo "Arquivo7: threshold.conf: permite ao usuário controlar o número de eventos necessários para gerar um alerta; e..."
echo "Arquivo8: unicode.map: fornece o mapeamento entre linguagens Unicode e o identificador..."

cd ~/snort_src/snort-2.9.13/etc/
sudo cp *.conf* /etc/snort
sudo cp *.map /etc/snort
sudo cp *.dtd /etc/snort
cd ~/snort_src/snort-2.9.13/src/dynamic-preprocessors/build/usr/local/lib/snort_dynamicpreprocessor/
sudo cp * /usr/local/lib/snort_dynamicpreprocessor/

echo "Editando o snort.conf..."
sed -i "s/include \$RULE\_PATH/#include \$RULE\_PATH/" /etc/snort/snort.conf

echo "Modifique manualmente o arquivo na linha 45-->ipvar HOME_NET Sua Rede 192.168.1.0/24..."

echo "Edite manualmente e acrescente no /etc/snort/rules/local.rules..."
echo "var RULE_PATH /etc/snort/rules    "
echo "var SO_RULE_PATH /etc/snort/so_rules   "
echo "var PREPROC_RULE_PATH /etc/snort/preproc_rules   "
echo "var WHITE_LIST_PATH /etc/snort/rules/iplists   "
echo "var BLACK_LIST_PATH /etc/snort/rules/iplists   "
echo "include $RULE_PATH/local.rules   "

echo "Teste as confiuracoes..."
sudo snort -T -c /etc/snort/snort.conf -i eth0

echo "Fim...Até Mais!"
