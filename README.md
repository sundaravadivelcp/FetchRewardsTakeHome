# # Fetch Rewards Hiring:Android App

This project is an Android app developed in Java. It retrieves and displays data from the [Fetch Hiring JSON endpoint](https://fetch-hiring.s3.amazonaws.com/hiring.json) adhering to the following requirements:

- Display all the items grouped by "listId"
- Sort the results first by "listId" and then by "name" while displaying
- Filter out any items where the "name" is blank or null
- Display the results in an easy-to-read list format
- Ensure the project is buildable on the latest (non-pre release) tools and compatible with the current release mobile OS

## Features

- Retrieves JSON data from a remote endpoint and parses it into Java objects
- Filters out items with null or blank "name" fields
- Groups items by "listId"
- Sorts items first by "listId" and then by "name," considering the numerical components in the "name" field for logical ordering
- Displays the final list in a user-friendly interface, leveraging a ExpandableListView for an efficient display of the data

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

Ensure you have the following software installed:
- Android Studio (latest stable version)
- Android SDK (latest non-pre-release version)
- Java SDK (Java 1.8 or above)
- An emulator or an Android device (Android version 11 and above)

### Installation

1. Clone the repository to your local machine using the following command:

    ```sh
    git clone https://github.com/yourusername/your-repository-name.git
    ```

2. Open the project in Android Studio.
3. Build and run the project on an emulator or an Android device.

### Libraries Used

- Retrofit: For network requests to fetch the JSON data
- Gson: To convert Java Objects into JSON and vice versa.

## Usage

Once you open the app, it automatically fetches, processes, and displays the data — grouped and sorted according to the specified requirements — in an easy-to-read format.
