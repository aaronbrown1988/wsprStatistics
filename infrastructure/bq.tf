resource "google_bigquery_dataset" "wspr_data" {
  dataset_id                  = "wspr_data"
  friendly_name               = "Wspr Data"
  description                 = "This is a test description"
  location                    = "US"
  default_table_expiration_ms = 3600000

  labels = {
    env = "default"
  }

}

resource "google_bigquery_table" "spot_table" {
  dataset_id = "${google_bigquery_dataset.wspr_data.dataset_id}"
  table_id   = "spot_table"

  time_partitioning {
    type = "DAY"
  }

  labels = {
    env = "default"
  }

  schema = "${file("schema.json")}"
}