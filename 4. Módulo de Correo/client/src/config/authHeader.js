export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('currentUser'));

    if (user && user.jwt) {
        return { Authorization: `Bearer ${user.jwt}` };
    } else {
        return {};
    }
}