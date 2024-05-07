output "servers" {
  value = {
    movie_bot_server = openstack_compute_instance_v2.movie_bot_server.access_ip_v4
    movie_bot_client = openstack_compute_instance_v2.movie_bot_client.access_ip_v4
  }
}
