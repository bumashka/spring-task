# Define required providers
terraform {
required_version = ">= 0.14.0"
  required_providers {
    openstack = {
      source  = "terraform-provider-openstack/openstack"
      version = "~> 1.53.0"
    }
  }
}

# Configure the Vault Provider by $VAULT_ADDR and $VAULT_TOKEN
provider "vault" {
  address = "http://localhost:8200"
}

data "vault_kv_secret_v2" "auth_secret" {
  mount = "kv"
  name  = "openstack-auth"
}

# Configure the OpenStack Provider
provider "openstack" {
  user_name   = data.vault_kv_secret_v2.auth_secret.data["username"]
  tenant_id   = data.vault_kv_secret_v2.auth_secret.data["project_id"]
  password    = data.vault_kv_secret_v2.auth_secret.data["password"]
  auth_url    = data.vault_kv_secret_v2.auth_secret.data["auth_url"]
}

# Define security group
resource "openstack_networking_secgroup_v2" "buldakova_secgroup" {
  name        = "buldakova_secgroup"
  description = "Security group for ssh and http/https"
}

resource "openstack_networking_secgroup_rule_v2" "default_rule" {
  direction         = "ingress"
  ethertype         = "IPv4"
  protocol          = "tcp"
  port_range_min    = 1
  port_range_max    = 20000
  remote_ip_prefix  = "0.0.0.0/0"
  security_group_id = openstack_networking_secgroup_v2.buldakova_secgroup.id
}

resource "openstack_compute_instance_v2" "movie_bot_client" {
  name        = "movie_bot_client"
  image_name  = var.image_name
  flavor_name = var.flavor_name
  key_pair    = var.key_pair
  security_groups = [openstack_networking_secgroup_v2.buldakova_secgroup.name]

  network {
    name = var.network_name
  }
}

resource "openstack_compute_instance_v2" "movie_bot_server" {
  name        = "movie_bot_server"
  image_name  = var.image_name
  flavor_name = var.flavor_name
  key_pair    = var.key_pair
  security_groups = [openstack_networking_secgroup_v2.buldakova_secgroup.name]

  network {
    name = var.network_name
  }
}
