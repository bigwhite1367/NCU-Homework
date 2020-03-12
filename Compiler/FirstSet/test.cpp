#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdbool.h>

int main() {
    int             ret, fd;
    char            buf[4096];
    char			endRead[4096];
    ssize_t         nread, nwrite;
    struct timeval  timeout;
    struct addrinfo hints, *servinfo, *p;

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_flags = AI_PASSIVE;

    if((ret = getaddrinfo("140.115.81.130", "7888", &hints, &servinfo))) {
        fprintf(stderr, "getaddrinfo(): %s\n", gai_strerror(ret));
        exit(1);
    }//end if

    for(p = servinfo; p ; p = p->ai_next) {
        fd = socket(p->ai_family, p->ai_socktype, p->ai_protocol);
        if(fd < 0) {
            perror("socket()");
            continue;
        }//end if

        //connect timeout
        timeout.tv_sec = 5;
        timeout.tv_usec = 0;

        if(setsockopt(fd, SOL_SOCKET, SO_SNDTIMEO, (char *)&timeout, sizeof(timeout)) < 0) {
            perror("setsockopt()");
            close(fd);
            continue;
        }//end if

        if(connect(fd, p->ai_addr, p->ai_addrlen) != 0) {
            perror("connect()");
            close(fd);
            continue;
        }//end if

        break;
    }//end for

    if(p == NULL) {
        fprintf(stderr, "connect failed\n");
        freeaddrinfo(servinfo);
        exit(1);
    }//end if

    freeaddrinfo(servinfo);

    strcpy(endRead, "END_OF_GRAMMAR");
    while(true) {
        nread = read(STDIN_FILENO, buf, sizeof(buf));
        if(nread <= 0) {
            break;
        }//end if

        nwrite = write(fd, buf, nread);
        if(nwrite <= 0) {
            break;
        }//end if
        if(strcmp(endRead, buf)+10 == 0)
        {
            break;
        }
    }//end while
    close(fd);

    return 0;
}//end main
