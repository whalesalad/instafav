# Instafav

### Features

- Run a webserver to allow a user to login with oauth
- store the user data in some simple database (memory?)
- Allow user to select people they would like to track, associate this with their account
- Allow user to enter certain notification methods, like email or SMS

### Notifications

- Email notification: send an email containing the photo as an attachment: need email address
- SMS notification: send an SMS to the user's phone number: need phone number

### Components

##### Website

- Welcome/Login Page
- Dashboard
  - Friend List
    - Giant list, clicking a name toggles it
    - Search box with live updates
    - Selected users are automatically moved to the top of the list
    - Automatically save when changes are made
    - Limit to 10 favorites.
  - Notifications Panel
    - Email box to enter an email address (html5 input type)
    - Phone box to enter phone number (html5 input type)
    - Entering values into either field will enable that feature.

##### Nice to Have

- Track notifications that have been sent? Useful for debugging certainly.

## Usage

FIXME: explanation

    $ java -jar instafav-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.
