<!-- (This is a comment) INSTRUCTIONS: Go through this page and fill out any **bolded** entries with their correct values.-->

# AND101 Project 5 - Choose Your Own API

Submitted by: **Bryan Tineo**

Time spent: **4** hours spent in total

## Summary

**Nasa: Mars Rover Photos of Mars** is an android app that **provides an easy way to interact with Nasa's API by allowing users to easily access one by one each photo Mars Rover does on Mars throughout its trip🌌🔭🌑**

If I had to describe this project in three (3) emojis, they would be: **📱🔭🌌**

## Application Features

<!-- (This is a comment) Please be sure to change the [ ] to [x] for any features you completed.  If a feature is not checked [x], you might miss the points for that item! -->

The following REQUIRED features are completed:

- [x] Make an API call to an API of your choice using AsyncHTTPClient
- [x] Display at least three (3) pieces of data for each API entry retrieved
- [x] A working Button requests a new API entry and updates the data displayed

The following STRETCH features are implemented:

- [ ] Add a query to the API request
    - The query I added is **FILL IN HERE**
- [ ] Build a UI to allow users to add that query

The following EXTRA features are implemented:

- [ ] List anything else that you added to improve the app!

## API Choice

My chosen API for this project is **[Nasa API](https://api.nasa.gov/)**.

## Video Demo

Here's a video / GIF that demos all of the app's implemented features:

<img src='http://i.imgur.com/e5ba6uE.gif' title='Video Demo' width='' alt='Video Demo' />

GIF created with [ScreenToGif](https://www.screentogif.com/)**

<!-- Recommended tools:
- [Kap](https://getkap.co/) for macOS
- [ScreenToGif](https://www.screentogif.com/) for Windows
- [peek](https://github.com/phw/peek) for Linux. -->

## Notes

- I learned how to Fetch API's an make an interactive app by using json data retrieved from the internet. Also, I learned how to setup the files in order to accept internet requests from http and https, so i had to add a "network_security_config.xml" on "res > xml" folder in order to accept http request and than i had to modify the "AndroidManifest.xml" and add "android:networkSecurityConfig="@xml/network_security_config" on the "<application>" tag in order to point to that configuration. I learned a lot on this project that would be difficult to writte all, but the most interesting part was to make my app to accept http request.

## License

Copyright **2024** **Bryan Tineo**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
