/* eslint-disable */

import axios from 'axios';

const state = {
    destination:'',
    nriders:'',
    requests: [{rideid:"ride1",user_name:"charles", driver_name:"edward", riders: 3, destination: "131 3rd Ave"}],
    rides:[],
    requestid:''

};

const getters = {
    getRideRequests: state => state.requests,
    getRequestID: state => state.requestid

  };

const actions = {
    
    async setRequestID({commit}, requestid){
        commit('setRequestID', requestid)
      },

    async addRequest({commit}, request){
        commit('addRideRequest', request)
      },

  };

const mutations = {
    addRideRequest: (state, request) => (state.requests.push(request)),
    setRequestID: (state, request) => (state.requestid = request)
};

export default {
  state,
  getters,
  actions,
  mutations
};
