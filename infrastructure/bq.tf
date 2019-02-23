resource "google_bigquery_dataset" "wspr_data" {
  dataset_id                  = "wspr_data"
  friendly_name               = "Wspr Data"
  description                 = "This is a test description"
  location                    = "US"

  labels = {
    env = "default"
  }

}

resource "google_bigquery_table" "spot_table" {
  dataset_id = "${google_bigquery_dataset.wspr_data.dataset_id}"
  table_id   = "spot_table"

  time_partitioning {
    type = "DAY",
    field = "timestamp"
  }

  labels = {
    env = "default"
  }

  schema = "${file("bq_schemas/spot_table.json")}"
}

resource "google_bigquery_dataset" "reference" {
  dataset_id                  = "reference_data"
  friendly_name               = "Reference Data"
  description                 = "This is a test description"
  location                    = "US"

  labels = {
    env = "default"
  }

}


resource "google_bigquery_dataset" "solar" {
  dataset_id                  = "solar_data"
  friendly_name               = "Solar Data"
  description                 = "This is a test description"
  location                    = "US"

  labels = {
    env = "default"
  }

}

resource "google_bigquery_table" "call_country" {
  dataset_id = "${google_bigquery_dataset.reference.dataset_id}"
  table_id = "big_cty"


  labels ={
    env = "default"
  }

  schema ="${file("bq_schemas/big_cty.json")}"
}

resource "google_bigquery_table" "muf" {
  dataset_id = "${google_bigquery_dataset.solar.dataset_id}"
  table_id = "muf"


  schema = "${file("bq_schemas/muf.json")}"
}

resource "google_bigquery_table" "xray" {
  dataset_id = "${google_bigquery_dataset.solar.dataset_id}"
  table_id = "xray"


  schema = "${file("bq_schemas/xray.json")}"
}