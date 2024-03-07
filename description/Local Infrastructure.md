The objective is to create the specified infrastructure using virtual machines within a local environment and subsequently deploy backend and frontend parts onto these machines.
![[local infrastucture.jpg]]
The following steps needed to be completed (just like example):
1. Setting up a virtual machine on Oracle VirtualBox
2. Creating VM clones and a virtual network
3. Installing PostgreSQL
4. Creating an S3 storage and access user
5. Installing Java and building the backend application
6. Installing Nginx and building the frontend application
7. Checking the website's functionality

#  Oracle VirtualBox VM configuration
The operating system Ubuntu 22.04 was chosen to create the basic virtual machine because earlier versions, such as Ubuntu 18, had outdated C/C++ libraries incompatible with the latest versions of Node.js. Attempting to use Ubuntu 18 resulted in errors during installation. While it was possible to manually update the libraries, it was easier to use Ubuntu 22.04 instead.
![[Pasted image 20240201173701.png]]
For the virtual machine, 1 GB of RAM and 1 CPU core were allocated. Network settings have not been changed at this stage. The size of the hard disk was set to 8 GB.
![[Pasted image 20240201173746.png]]
![[Pasted image 20240201173823.png]]
It was very important during the installation of Ubuntu to check the box for installing the OpenSSH server. This will allow remote connection to the virtual machine via SSH in the future. Remote work via SSH is much more convenient, especially when entering long passwords, tokens, and links.
![[Pasted image 20240204094837.png]]
After installing the operating system on a virtual machine, it is recommended to immediately update the package repositories using the command:
```
sudo apt update
```
While updating, there was found an error with an outdated repository. To resolve this issue, the configuration file of repositories was edited, where one line was commented out. After that, the error disappeared, and the update was successful.
![[Pasted image 20240203132935.png]]
First, a "clean" basic virtual machine was created, which can then be cloned to create frontend and backend machines. Such a base VM can be reused for cloning in case of issues with already created clones.
# Creating VM clones and virtual networks 
Next, 2 full clones of the base virtual machine were created - for the frontend and backend.
![[Pasted image 20240202142551.png]]
![[Pasted image 20240202142603.png]]
For machines to communicate with each other, they must be on the same local virtual network and have different IP addresses. To achieve this, a shared NAT network with internet access was created in the VirtualBox settings, and network parameters were configured.
![[Pasted image 20240202145813.png]]
![[Pasted image 20240202150102.png]]
Then both virtual machines were placed into this new network.
![[Pasted image 20240202150431.png]]
![[Pasted image 20240202150553.png]]
However, by default, virtual machines were assigned the same IP address `10.0.2.15`. To be able to address the machines with different addresses, one of them needed to change its IP.
```
ip a
```
The command `ip a` showed that both VMs have the same IP address on the `enp0s3` interface.
The first machine:
![[Pasted image 20240202152347.png]]
The second machine:
![[Pasted image 20240202152403.png]]
To change the IP, the network configuration file of the netplan was edited on the first (frontend) virtual machine:
```
sudo nano /etc/netplan/00-installer-config.yaml
```
![[Pasted image 20240202152513.png]]
Thus, the virtual machines were placed on the same network and were assigned different IP addresses. The first clone has the IP ```10.0.2.10```, while the second one has ```10.0.2.15```. After that, the virtual machine needs to be restarted.
![[Pasted image 20240202173948.png]]
To test the communication between virtual machines, the ping utility was used. It confirmed that the machines can communicate with each other over the network.
From the first clone to the second:
![[Pasted image 20240202174532.png]]
From the second  clone to the first:
![[Pasted image 20240202174557.png]]
An important step for the infrastructure setup was configuring port forwarding. Although the virtual machines had internet access through NAT, they couldn't be accessed locally due to closed ports.
For convenient access to the virtual machines via SSH terminal, the following ports were opened:
- `2001:22` - Port `2001` on the host was forwarded to `10.0.2.10:22` for SSH access to the web server.
- `2002:22` - Port `2002` on the host was forwarded to `10.0.2.15:22` for SSH access to the backend.
Additionally, ports were opened to access the web server and backend application:
- `8081:80` - Port `8081` on the host was forwarded to `10.0.2.10:80` for accessing Nginx.
- `8080:8080` - Port `8080` on the host was forwarded to `10.0.2.15:8080` for accessing the Java backend application.
![[Pasted image 20240206193616.png]]
After that, it became possible to conveniently work with virtual machines via SSH at specified addresses and ports.
![[Pasted image 20240203160601.png]]
![[Pasted image 20240203143536.png]]
Next, it was necessary to determine the order of requests: frontend -> backend -> database and S3. Therefore, the next step was to create a database and S3 storage.
# Installing PostgreSQL
PostgreSQL could be deployed as a service on AWS or locally on the backend's virtual machine. However, 1 GB of RAM on the VM was insufficient for local installation. Therefore, the decision was made to use a personal server [Oracle](https://www.oracle.com/uk/cloud/free/) Cloud, which provides powerful free instances.
On the Oracle server, a ready-made PostgreSQL image was deployed in a Docker environment using docker-compose. Example file:
![[Pasted image 20240204144040.png]]
To create a container in the directory with the docker-compose.yml file, the following command was executed:
```
sudo docker compose up -d
```
The main goal was to obtain the server address where the database is deployed and the port for further configuring access to it from the backend application.
# Create S3 Bucket and configure User Access 
To store website images, an S3 cloud storage on AWS was used. The following steps were taken to create it:
1. The S3 storage itself was created on the AWS.
![[Pasted image 20240204145255.png]]
2. A user with read and write permissions was created in the storage.
![[Pasted image 20240203161627.png]]
3. Access keys to S3 have been generated and saved for the user.
![[Pasted image 20240203161643.png]]
Thus, an image repository was prepared and access to it was configured.
# Installing Java and building the backend application:
![[Pasted image 20240206182621.png]]
To install and configure the backend application, the following steps were performed:
Java was installed on the backend virtual machine via SSH:
```
sudo apt update
sudo apt install openjdk-17-jre-headless
java --version
```
It was important to verify the installed version of Java and know the path to the openjdk directory for further compilation.
Next, the repository with the backend Java application source code was cloned from the **dev** branch.
```
git clone https://github.com/ProvedCode/backend.git
cd backend/
git switch dev
ll
```
![[Pasted image 20240206085114.png]]
Before building the application, the following environment variables were specified:
- ```JAVA_HOME``` - path to Java
- ```S3_REGION``` - name of the S3 storage region
- ```BUCKET``` - name of the S3 storage bucket
- ```S3_REGION, S3_ACCESS_KEY``` - S3 access keys
- ```DB_URL``` - connection link to the database
- ```DB_LOGIN, DB_PASSWORD``` - access keys to the database
- ```EMAIL_USER, EMAIL_PASSWORD``` - access keys to the email service
- ```SPRING_PROFILES_ACTIVE``` - for building
```
rm -f .env

#Export variables

export JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"

export S3_REGION="eu-central-1"
export BUCKET="provedcode-backend"
export S3_ACCESS_KEY="AKIA5FTZDX7SRTYGK4PR"
export S3_SECRET_KEY="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"

export DB_LOGIN="provedcode"
export DB_PASSWORD="FemQcpYd9MsmaSWW8LKY"
export DB_URL="129.151.222.203:5432/provedcode"

export SPRING_PROFILES_ACTIVE="prod"

export EMAIL_USER="proved.code.team@gmail.com"
export EMAIL_PASSWORD="qqgtxxenjiesrrnn"
```
![[Pasted image 20240206165838.png]]
The build was initiated with the following commands:
```
chmod 744 ./mvnw
./mvnw clean package
```
After a successful build, the backend application logs display the message ```[INFO] BUILD SUCCESS```.
![[Pasted image 20240206170129.png]]
The assembled application file was then renamed and moved to the necessary directory for deployment. To run the application in the background, a **systemd** unit file was written, specifying the launch command with the path to the application file and port in the **ExecStart** directive:
![[Pasted image 20240206174350.png]]
![[Pasted image 20240206181444.png]]
After installing the service unit, the service was enabled with the following commands:
```
sudo systemctl enable provedcode --now
sudo systemctl status provedcode
```
However, errors occurred during the first launch due to the absence of environment variables.
![[Pasted image 20240206175620.png]]
To solve the issue, the service was stopped, and a separate configuration was created with the variables specified:
```
sudo systemctl stop provedcode || true
sudo mkdir /etc/systemd/system/provedcode.service.d
sudo nano /etc/systemd/system/provedcode.service.d/provedcode.conf
```
![[Pasted image 20240206181150.png]]
After the reboot, the service started working correctly.
```
sudo systemctl enable provedcode --now
sudo systemctl status provedcode
```
![[Pasted image 20240206181802.png]]
In case of errors, you can check the logs:
```
journalctl -u provedcode.service 
```
![[Pasted image 20240206182306.png]]
Thus, the backend application was configured to auto-start in the background mode via systemd.
# Installing Nginx and building the frontend application
Deploying the frontend required remote access to the virtual machine. Connection was established via SSH using a pre-configured port on the host machine.
![[Pasted image 20240203175712.png]]
The Nginx web server was installed using the following commands:
```
sudo apt update
sudo apt install nginx -y
```
After installation, the status of the service was checked, and if necessary, the restart command was executed.
```
sudo systemctl status nginx 
```
![[Pasted image 20240206183531.png]]

If the status of the service is inactive, it's worth restarting it:
```
sudo systemctl restart nginx 
```
Access to port 80 for Nginx was allowed in iptables:
```
sudo ufw allow OpenSSH
sudo ufw enable
sudo ufw allow 'Nginx HTTP'
```
  Checking firewall permissions:
```
sudo ufw status
```
Now, if you go to port 80 in the browser on localhost, the default Nginx page will be displayed.
![[Pasted image 20240202180442.png]]
The page is located in the directory ```/var/www/html/``` and has the name ```index*.html```.
```
cat /var/www/html/index*.html
```
![[Pasted image 20240202181051.png]]
Before installing Node.js, it's important to understand which version to install. For example, the 18th version will soon be deprecated, while the current version for now according the official Node.js website is the 21st.
![[Pasted image 20240202182255.png]]
At the time of deployment, version 21 was installed using the following commands:
```
cd ~
curl -sL https://deb.nodesource.com/setup_21.x -o nodesource_setup.sh
sudo bash nodesource_setup.sh
sudo apt install nodejs
```
Installed versions were checked using the commands `node -v` and `npm -v`.
```
node -v
npm -v
```
Thus, all necessary steps for deploying the React application were completed on the frontend virtual machine.
![[Pasted image 20240206184907.png]]
The source code of the React frontend application was obtained by cloning the repository from the **dev** branch using git clone.
```
git clone https://github.com/ProvedCode/frontend.git
cd frontend/
git switch dev
ll
```
![[Pasted image 20240206185921.png]]
To build, you need to install libraries using the **npm** manager:
```
npm install --omit=dev
```
![[Pasted image 20240206190823.png]]
The variable REACT_APP_API_URL with a link to the backend was exported as follows:

```
export REACT_APP_API_URL="http://127.0.0.1:8080"
```

Unfortunately, the frontend developer set a static IP address for another backend server, so the content of the source code file had to be changed from this:
![[Pasted image 20240208112013.png]]
To as followed
![[Pasted image 20240208112152.png]]
Start application build:
```
npm run build
```
![[Pasted image 20240206191201.png]]
When there was insufficient memory for building, more RAM was allocated to the virtual machine.
![[Pasted image 20240203183452.png]]
The successful completion of the frontend application build should look like this.
![[Pasted image 20240206191845.png]]
The compiled application files were copied to the directory `/var/www/html`, from where Nginx could access the static files.
```
chmod -R 755 ./build/*
sudo rm -rf /var/www/html/*
sudo mv ./build/* /var/www/html/
ll /var/www/html/
```
![[Pasted image 20240206192712.png]]
Thus, the frontend application was built and deployed on an Nginx server.
# Website Functionality Check
The final step involved checking the functionality of the deployed web application. To do this, entering the address of the frontend virtual machine in the browser on port 8081 should display the main screen of the application with a list of items.
![[Pasted image 20240206203820.png]]

![[Pasted image 20240206203835.png]]
 If the list didn't display, it indicated a frontend-backend or database interaction issue. Possible reasons could include:
- Unavailability of the backend application at the address specified in the environment variable REACT_APP_API_URL
- Backend connection error to the database
- Incorrect data in the database
In such cases, it was necessary to check the backend's operation, its logs for errors, and also the data in the database.
Thus, displaying the main page of the application at the virtual machine's address indicated the successful deployment of the entire infrastructure.
