let firebaseConfig;
if (location.hostname === "localhost") {
    firebaseConfig = {
        apiKey: "AIzaSyBoLKKR7OFL2ICE15Lc1-8czPtnbej0jWY",
        projectId: "ds-theatres",
    }
} else {
    firebaseConfig = {
        apiKey: "AIzaSyDhatz5atmyJV7x4VgrZXRhqSgcpUqUrBE",
        authDomain: "ds-theatres.firebaseapp.com",
        projectId: "ds-theatres",
        storageBucket: "ds-theatres.appspot.com",
        messagingSenderId: "628136502815",
        appId: "1:628136502815:web:13835cc7176753adee572a",
        measurementId: "G-1JZ25W0V5G"
    };
}
firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();

if (location.hostname === "localhost") {
    auth.useEmulator("http://localhost:8082");
}
const ui = new firebaseui.auth.AuthUI(auth);

ui.start('#firebaseui-auth-container', {
    signInOptions: [
        firebase.auth.EmailAuthProvider.PROVIDER_ID
    ],
    callbacks: {
        signInSuccessWithAuthResult: function (authResult, redirectUrl) {
            auth.currentUser.getIdToken(true)
                .then(async (idToken) => {
                    await fetch("/authenticate", {
                        method: "POST",
                        body: idToken,
                        headers: {
                            "Content-Type": "plain/text"
                        }
                    });
                    location.assign("/");
                });
            return false;
        },
    },
});
