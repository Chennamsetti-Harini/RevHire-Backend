pipeline{
    agent any
    tools{
        jdk "JDK21"
        maven "Maven3"
    }
    options {
        skipStagesAfterUnstable()
    }
    environment{
        JAR_NAME = "revhire.jar"
        REMOTE_DIR = "/home/ec2-user"
        APP_PORT = "8080"
    }
    stages{
        stage("Checkout"){
            steps{
                checkout scm
            }
        }
        stage("Build"){
            steps{
                bat 'mvn -B clean compile'
            }
            post{
                success{
                    echo "build successful"
                }
                failure{
                    echo "build failed"
                }
            }
        }
        stage("Test"){
            steps{
                bat 'mvn -B test'
            }
            post{
                success{
                    echo "test successful"
                }
                failure{
                    echo "test failed"
                }
            }
        }
        stage("Package"){
            steps{
                bat 'mvn -B package -DskipTests'
            }
            post{
                success{
                    echo "jar created successfully"
                }
                failure{
                    echo "jar creation failed"
                }
            }
        }
        stage("Deploy to EC2"){
            steps{
                sshPublisher(
                    publishers:[
                        sshPublisherDesc(
                            configName:"ec2-server",
                            verbose: true,
                            transfers:[
                                sshTransfer(
                                    sourceFiles:"target/${JAR_NAME}",
                                    removePrefix: "target/",
                                    remoteDirectory:"${REMOTE_DIR}",
                                    flatten: true,
                                    execTimeout: 120000,
                                    execCommand:"""
                                        pkill -f 'java -jar' || true
                                        sleep 2
                                        nohup java -jar /home/ec2-user/revhire.jar --spring.profiles.active=prod > /home/ec2-user/application.log 2>&1 &
                                        disown
                                        exit 0
                                    """
                                )
                            ]
                        )
                    ]
                )
            }
            post {
                success{
                    echo "deployed successfully"
                }
            }
        }
    }
}