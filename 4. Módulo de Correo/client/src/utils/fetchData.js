const baseURL = "http://localhost:8083/api/v1";

export const getData = async (url, token) => {

    const headers = token ? {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
    }  : { 'Content-Type': 'application/json' }

    const res = await fetch(`${baseURL}/${url}`, {
        method: 'GET',
        headers: headers,
    })

    const data = await res.json()

    return data
}

export const postData = async (url, post, token) => {

    const headers = token ? {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
    }  : { 'Content-Type': 'application/json' }

    const res = await fetch(`${baseURL}/${url}`, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(post)
    })

    const data = await res.json()
    return data
}

export const putData = async (url, post, token) => {

    const headers = token ? {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
    }  : { 'Content-Type': 'application/json' }

    const res = await fetch(`${baseURL}/${url}`, {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(post)
    })

    const data = await res.json()

    return data
}

export const patchData = async (url, post, token) => {
    const res = await fetch(`${baseURL}/${url}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
        body: JSON.stringify(post)
    })

    const data = await res.json()

    return data
}

export const deleteData = async (url, token) => {
    const res = await fetch(`${baseURL}/${url}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
    })

    const data = await res.json()

    return data
}