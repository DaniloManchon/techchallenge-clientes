terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.66.0"
    }
  }
}

provider "helm" {
  kubernetes {
    host                   = data.aws_eks_cluster.cluster.endpoint
    cluster_ca_certificate = base64decode(data.aws_eks_cluster.cluster.certificate_authority[0].data)
    exec {
      api_version = "client.authentication.k8s.io/v1beta1"
      args        = ["eks", "get-token", "--cluster-name", var.cluster_name, "--output", "json"]
      command     = "aws"
    }
  }
}

data "aws_eks_cluster" "cluster" {
  name = var.cluster_name
}

provider "aws" {
  region = var.aws_region
}

resource "helm_release" "app" {
  name             = var.app_name
  repository       = "${path.module}/helm"
  chart            = var.app_name
  namespace        = var.app_name
  create_namespace = true
}

# Configure backend - terraform.tfstate
terraform {
  backend "s3" {
    bucket = "tech-challenge-terraform-tfstate"
    key    = "terraform-backend.tfstate"
    region = "us-east-1"
  }
}