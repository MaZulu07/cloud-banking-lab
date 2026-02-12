resource "kubernetes_deployment_v1" "account" {
  metadata {
    name   = "account-deployment"
    labels = { app = "account-service" }
  }

  spec {
    replicas = 1
    selector { match_labels = { app = "account-service" } }
    template {
      metadata { labels = { app = "account-service" } }
      spec {
        container {
          name  = "account-service"
          image = "mazulu/account-service:latest"
          port { container_port = 8001 }
        }
      }
    }
  }
}

resource "kubernetes_service" "account" {
  metadata {
    name = "account-service"
  }

  spec {
    selector = {
      app = "account-service"
    }

    port {
      port        = 8001
      target_port = 8001

    }


  }
}
