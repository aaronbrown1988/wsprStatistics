provider "google" {
  project = "wsprstats-163301"
  region  = "us-central1"
  zone    = "us-central1-c"
}

resource "google_storage_bucket" "raw-data" {
  name     = "wsprnet-raw-data"
  location = "US"
}
