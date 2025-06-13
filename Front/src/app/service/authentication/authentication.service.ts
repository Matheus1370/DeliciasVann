import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageDataSource } from '../../data/local-storage.datasource';
import { jwtDecode } from 'jwt-decode';

interface TokenData {
  sub: string;
}

export interface UserData {
  id: string;
  email: string;
  name: string;
  password: string;
  createdAt: string;
  // Add other user properties as needed
}

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8080';

  private _userToken: string | null = null; // Initialize as null

  private _userData: UserData | null = null; // Initialize as null

  constructor(private http: HttpClient) {
    // Check if the user token is already stored in local storage
    const storedToken = LocalStorageDataSource.getToken();
    if (storedToken) {
      this.userToken = storedToken;
      this._userData = LocalStorageDataSource.getUserData();
    }
  }

  login(email: string, password: string) {
    return this.http.post<{ access_token: string }>(
      this.baseUrl + '/customer/auth',
      {
        email,
        password,
      }
    );
  }

  register(email: string, password: string, name: string) {
    return this.http.post(this.baseUrl + '/customer', {
      email,
      password,
      name,
    });
  }

  updateUserData(userData: UserData) {
    this._userData = userData;
    LocalStorageDataSource.updateUserData(userData); // Store updated user data in local storage
    return this.http
      .put<UserData>(this.baseUrl + '/customer/' + userData.id, userData)
      .subscribe({
        next: (data) => {},
        error: (error) => {
          console.error('Error updating user data:', error);
          // Handle error appropriately, e.g., show a notification
        },
      });
  }

  get userToken() {
    return this._userToken;
  }

  set userToken(token: string | null) {
    this._userToken = token;
    if (token) {
      LocalStorageDataSource.updateToken(token);
      const tokenData = jwtDecode(token) as TokenData;
      this.http
        .get<UserData>(this.baseUrl + '/customer/' + tokenData.sub)
        .subscribe(
          (data: UserData) => {
            this._userData = data; // Store user data on successful fetch
            LocalStorageDataSource.updateUserData(data);
          },
          (error) => {
            console.error('Error fetching user data:', error);
            this._userData = null; // Reset user data on error
          }
        );
    } else {
      LocalStorageDataSource.clearToken();
    }
  }

  get userData() {
    return this._userData;
  }
}
